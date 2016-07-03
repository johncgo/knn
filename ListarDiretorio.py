import os



x = os.listdir('moto')
print x
b = [i+'\n' for i in x]

arq = open("lista.txt", 'w')

arq.writelines(b)

arq.close()
