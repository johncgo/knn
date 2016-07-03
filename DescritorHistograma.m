tic
%dir = 'C:\Users\Junior\Desktop\Fotos banhistas\Fotos\';
dir = 'C:\Users\Junior\Desktop\testes\moto\';
%fid = fopen('C:\Users\Junior\Desktop\Fotos banhistas\Fotos\nomesImagens.txt');
fid = fopen('C:\Users\Junior\Desktop\testes\lista.txt');
nomesTeste = textscan(fid , '%s' , 'delimiter' , '\n');
nomesTeste=nomesTeste{1} ;
fclose(fid);
arq = fopen('C:\Users\Junior\Desktop\Segmentação atualizada 05-06-16\PraiaNaoPraia\descritores3.txt', 'w');
for aux = 1:size(nomesTeste,1)
    nome = nomesTeste(aux,1);
    nome = char(nome);
    nome = strtrim(nome);
    fileName2 = nome;
    
    im1 = imread(char(strcat(dir, fileName2)));
    if size(im1,3) == 3
        im11 = im1;
    else
        im11 = [im1;im1;im1];
    end
    im2 = imresize(im11, [128 128]);
    
    imR = im2(:,:,1);
    vetorR = imhist(imR,50);
    
    imG = im2(:,:,2);
    vetorG = imhist(imG,50);
    
    imB = im2(:,:,3);
    vetorB = imhist(imB,50);
    
    vetor = [vetorR; vetorG; vetorB];
    vetor = vetor / norm(vetor);
    %fprintf(arq, '%f', vetor);
    for i = 1:150
        fprintf(arq, '%f, ', vetor(i));
    end
    k = 2;
    fprintf(arq, '%d\n', k);
end
fclose(arq);
toc