import os

arq = open("BeaNbea.arff", 'w')

arq.writelines('@RELATION BnB\n')

for i in range(1, 151):
    arq.writelines('@ATTRIBUTE a' + str(i) + ' REAL\n')

arq.writelines('@ATTRIBUTE class     {0, 1}\n')
arq.writelines('@DATA\n')
arqD1 = open("descritores.txt", 'r')
arqD2 = open("descritores2.txt", 'r')
arqD3 = open("descritores.txt", 'r')

arq.writelines(arqD1.readlines())
arq.writelines('\n')
arq.writelines(arqD2.readlines())
arq.writelines('\n')
arq.writelines(arqD3.readlines())
           
arq.close()
arqD1.close()
arqD2.close()
arqD3.close()

