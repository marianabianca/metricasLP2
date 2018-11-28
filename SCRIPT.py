import requests
import sys
import urllib
import csv
import os
import zipfile
import unicodedata


def strip_accents(text):
    text = unicode(text, 'utf-8')
    text = unicodedata.normalize('NFD', text)
    text = text.encode('ascii', 'ignore')
    text = text.decode("utf-8")
    return str(text)


token = str(sys.argv[1])
lab = str(sys.argv[2])
dia_p = int(sys.argv[3])
mes_p = int(sys.argv[4])
dia_f = int(sys.argv[5])
mes_f = int(sys.argv[6])


# requisicao dos labs parciais e finais juntos
r = requests.get('https://canvas.instructure.com/api/v1/courses/1388632/assignments/' + lab + '/submissions?include[]=submission_history&per_page=500', headers={"Authorization": "Bearer " + token})

r = r.json()

# labs parciais {id: url_download}
alunos_p = {}

# labs finais {id: url_download}
alunos_f = {}

# coloca os links dos labs parciais e finais nos dicionarios 
for x in r:
	if "attachments" in x:
		id_aluno = x["user_id"]	
		for a in x["submission_history"]:
			aux_p = []
			aux_f = []
			for b in a["attachments"]:
				data = b["created_at"]
				mes = int(data[5:7])
				dia = int(data[8:10])
				if dia <= dia_p and mes <= mes_p:
					aux_p.append(b["url"])
				if dia <= dia_f and mes <= mes_f:
					aux_f.append(b["url"])
			if len(aux_p) > 0:
				alunos_p[id_aluno] = aux_p[len(aux_p)-1]
			if len(aux_f) > 0:
				alunos_f[id_aluno] = aux_f[len(aux_f)-1]

# importar csv
csv_alunos = csv.reader(open('alunos.csv'))

# dicionario com ids e nomes alunos
alunos = {}

# coloca ids e nomes no dicionario
for a in csv_alunos:
	if a[0] != 'id':
		alunos[a[0]] = a[1].replace(" ", "_")

# cria diretorios
if not os.path.exists("parciais"):
    os.makedirs("parciais")
if not os.path.exists("finais"):
    os.makedirs("finais")

# fazer download dos parciais
for l in alunos_p:
	down_url = alunos_p[l]
	nome = str(l)
	if nome in alunos:
		nome = alunos[str(l)]
	urllib.urlretrieve(down_url, os.path.join("parciais", nome + ".zip"))

# deszipa e renomeia
files = os.listdir('parciais/')
for file_ in files:
	if file_.lower().endswith('.zip'):
		try:
			os.system("unzip parciais/" + file_[:-4] + " -d parciais/" + file_[:-4])
			# fantasy_zip = zipfile.ZipFile("parciais/" + file_)
			# zipname = (fantasy_zip.infolist()[0].filename)[:-1]
			# fantasy_zip.extractall('parciais/')
			# fantasy_zip.close()
			# os.rename("parciais/" + zipname, "parciais/" + file_[:-4])
		except Exception as e:
			print("erro no zip >>>> : " + file_)
			print(e) 
os.system("rm -r parciais/*.zip")

