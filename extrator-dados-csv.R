classes1 = read.csv("csv lab 2 parcial/classes.csv")
linhas1 = read.csv("csv lab 2 parcial/linhas.csv")
metodos1 = read.csv("csv lab 2 parcial/metodos.csv")
testes1 = read.csv("csv lab 2 parcial/testes.csv")

classes2 = read.csv("csv lab 2 final/classes.csv")
linhas2 = read.csv("csv lab 2 final/linhas.csv")
metodos2 = read.csv("csv lab 2 final/metodos.csv")
testes2 = read.csv("csv lab 2 final/testes.csv")


# CLASSES
# PARCIAL
hist(classes1$Metrica, col=rgb(1,0,0,0.4), xlim=c(0, 8), ylim=c(0, 80), main="Número de classes por projeto parcial", xlab="Classes", ylab="Frequência")
# FINAL
hist(classes2$Metrica, col=rgb(0,0,1,0.3), xlim=c(0, 8), ylim=c(0, 80), main="Número de classes por projeto final", xlab="Classes", ylab="Frequência")
# PARCIAL E FINAL
hist(classes1$Metrica, col=rgb(1,0,0,0.4), xlim=c(0, 8), ylim=c(0, 80), main="Número de classes por projeto", xlab="Classes", ylab="Frequência")
hist(classes2$Metrica, col=rgb(0,0,1,0.3), add=T)


# LINHAS
# PARCIAL
hist(linhas1$Metrica, col=rgb(1,0,0,0.4), xlim=c(0, 2000), ylim=c(0, 50), main="Número de linhas por projeto parcial", xlab="Linhas", ylab="Frequência")
# FINAL
hist(linhas2$Metrica, col=rgb(0,0,1,0.3), xlim=c(0, 2000), ylim=c(0, 50), main="Número de linhas por projeto final", xlab="Linhas", ylab="Frequência")
# PARCIAL E FINAL
hist(linhas1$Metrica, col=rgb(1,0,0,0.4), xlim=c(0, 2000), ylim=c(0, 50), main="Número de linhas por projeto", xlab="Linhas", ylab="Frequência")
hist(linhas2$Metrica, col=rgb(0,0,1,0.3), add=T)


# METODOS
# PARCIAL
hist(metodos1$Metrica, col=rgb(1,0,0,0.4), xlim=c(0, 350), ylim=c(0, 80), main="Número de métodos por projeto parcial", xlab="Métodos", ylab="Frequência")
# FINAL
hist(metodos2$Metrica, col=rgb(0,0,1,0.3), xlim=c(0, 350), ylim=c(0, 80), main="Número de métodos por projeto final", xlab="Métodos", ylab="Frequência")
# PARCIAL E FINAL
hist(metodos1$Metrica, col=rgb(1,0,0,0.4), xlim=c(0, 350), ylim=c(0, 80), main="Número de métodos por projeto", xlab="Métodos", ylab="Frequência")
hist(metodos2$Metrica, col=rgb(0,0,1,0.3), add=T)


# TESTES
# PARCIAL
hist(testes1$Metrica, col=rgb(1,0,0,0.4), xlim=c(0, 10), ylim=c(0, 100), main="Número de testes por projeto parcial", xlab="Testes", ylab="Frequência")
# FINAL
hist(testes2$Metrica, col=rgb(0,0,1,0.3), xlim=c(0, 10), ylim=c(0, 100), main="Número de testes por projeto final", xlab="Testes", ylab="Frequência")
# PARCIAL E FINAL
hist(testes1$Metrica, col=rgb(1,0,0,0.4), xlim=c(0, 10), ylim=c(0, 100), main="Número de testes por projeto", xlab="Testes", ylab="Frequência")
hist(testes2$Metrica, col=rgb(0,0,1,0.3), add=T)
