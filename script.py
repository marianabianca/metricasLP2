import os
import re
import zipfile
import chardet
import fileinput
import platform
import os

from sys import platform as _platform

import unicodedata

def strip_accents(s):
   return ''.join(c for c in unicodedata.normalize('NFD', s)
                  if unicodedata.category(c) != 'Mn')

files = os.listdir('.')
for file_ in files:
	if file_.lower().endswith('.zip'):
		try:
			fantasy_zip = zipfile.ZipFile(file_)
			fantasy_zip.extractall('.')
			fantasy_zip.close()
		except:
			print("erro no zip >>>> : " + file_)


files = os.listdir('.')

def try_mkdir(dirname):
    if not os.path.isdir(dirname):
        os.makedirs(dirname)
        

def project(dirname, pname):
    project_base = """<?xml version="1.0" encoding="UTF-8"?>
                <projectDescription>
                    <name>""" + pname + """</name>
                    <comment></comment>
                    <projects>
                    </projects>
                    <buildSpec>
                        <buildCommand>
                            <name>org.eclipse.jdt.core.javabuilder</name>
                            <arguments>
                            </arguments>
                        </buildCommand>
                    </buildSpec>
                    <natures>
                        <nature>org.eclipse.jdt.core.javanature</nature>
                    </natures>
                </projectDescription>"""
    classpath_entries = '<classpathentry kind="src" path="src"/><classpathentry kind="src" path="testes"/>'
    classpath_base = """<?xml version="1.0" encoding="UTF-8"?>
                        <classpath>
                            <classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-1.8"/>""" + classpath_entries + """<classpathentry kind="con" path="org.eclipse.jdt.junit.JUNIT_CONTAINER/4"/>
                            <classpathentry kind="output" path="bin"/>
                        </classpath>"""
    project_file = open(dirname + ".project", 'w')
    project_file.write(project_base)
    project_file.close()
    classpath_file = open(dirname + ".classpath", 'w')
    classpath_file.write(classpath_base)
    classpath_file.close()


def findjava(namelist):
    java_files = []
    for fname in namelist:
        if fname.strip().endswith('.java'):
            java_files.append(fname)
    return java_files


project_p = re.compile("([a-zA-Z_]*[0-9]+)")
junit_pattern = re.compile(b'import.*junit.*')


for file_ in files:
    if file_.lower().endswith('.zip'):
        zfile = zipfile.ZipFile(file_)
        student = strip_accents(file_.split("_")[0])
        content = ''
        new_dir = {}
        for java_file in findjava(zfile.namelist()):
            raw = zfile.read(java_file)
            junit_file = False
            pkg = b''
            for line in raw.split(b'\n'):
                line = line.strip()
                if not pkg and line.startswith(b'package'):
                    pkg = line.strip().split()[1].strip().split(b';')[0]
                junit_match = junit_pattern.match(line)
                if junit_match:
                    junit_file = True
                if line.startswith(b'public class') or line.startswith(b'class'):
                    break  # no more imports or package
                new_dir[java_file] = ('tests' if junit_file else 'src') + os.sep + pkg.decode('utf-8').replace('.', os.sep)
        path = ""
        if _platform.startswith('linux'): #linux
                path = 'metricasLP2/Metricas/Projetos'
        elif os.name == 'nt':   #Windows
                path = 'metricasLP2\\Metricas\\Projetos'
        student_dir = path + os.sep + student
        try_mkdir(student_dir)
        student_files = []
        for zfile_, new_java_dir  in new_dir.items():
            try_mkdir(student_dir + os.sep + new_java_dir)
            contents = zfile.read(zfile_)
            zinfo = zfile.getinfo(zfile_)
            open(student_dir + os.sep + new_java_dir + os.sep + os.path.basename(zinfo.filename), 'wb').write(contents)
            project(student_dir + os.sep, student)
            
files = os.listdir('.')
fixed = []
for i in range(len(files)):
	if not files[i].lower().endswith('.zip'):
		fixed.append(files[i])
		
for j in range(len(files)):
	if files[j] not in fixed:
		os.remove(files[j])
		
