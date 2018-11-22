# coding: utf-8
import requests
import csv
import sys

token = str(sys.argv[1])
hd = {"Authorization": "Bearer " + token}

r = requests.get('https://canvas.instructure.com/api/v1/courses/1388632/users?per_page=100', headers=hd)
paginated = r.json()
while 'next' in r.links:
	r = requests.get(r.links['next']['url'], headers = hd)
	paginated.extend(r.json())

with open("alunos.csv", "w") as csvfile:
	fieldnames = ['id', 'short_name']
	writer = csv.DictWriter(csvfile, fieldnames=fieldnames)

	writer.writeheader()

	for aluno in paginated:
		id_a = aluno["id"]
		short_name = aluno["short_name"]
		writer.writerow({'id': id_a, 'short_name': short_name})
