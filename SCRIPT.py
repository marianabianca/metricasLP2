import requests
import sys
import urllib
import csv
import os

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
		id_aluno = x["id"]	
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
	alunos[a[0]] = a[1]

# cria diretorios
if not os.path.exists("parciais"):
    os.makedirs("parciais")
if not os.path.exists("finais"):
    os.makedirs("finais")

# fazer download dos parciais
for l in alunos_p:
	# nome = alunos[l]
	down_url = alunos_p[l]
	urllib.urlretrieve(down_url, os.path.join("parciais", str(l) + ".zip"))
