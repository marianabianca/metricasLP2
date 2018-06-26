import os


files = os.listdir('.')
contents = {}
res = ''
for file_ in files:
    print(file_)
    if os.path.isdir(file_) and os.path.isdir(file_ + os.sep + 'target'):
            site = '<a href=' + file_ + os.sep + 'target' + os.sep + 'site' + os.sep
            res += site + 'project-reports.html>' + file_ + '</a><br>'
            res += site + 'cobertura' + os.sep + 'index.html>cobertura</a><br>'
            res += site + 'findbugs.html>findbugs</a><br>'
            res += site + 'pmd.html>pmd</a><br>'
            res += site + 'cpd.html>cpd</a><br>'
            res += '<img width=100% src="' + file_ + os.sep + 'target' + os.sep + 'plantuml' + os.sep + 'Maven Quick Start Archetype.urm.png' +'"></img><br><br>'

open('res.html', 'w').write(res)
