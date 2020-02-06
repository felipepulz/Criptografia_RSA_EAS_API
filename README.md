* **Dependências**

   <p>SDK Java11</p>
   Instalar o OpenSSL de acordo com seu sistema operacional 

#### Geração do par de chaves usando OPENSSL
* **0ª Iniciando**

    <p>Você precisa ir até a raiz de instalação do OpenSSL para executar os comando a seguir</p>
    <p>No windowns por exemplo</p>
    <p>Abra o cmd e navegue até a raiz de instalação</p>
    <p>cd.. (...\OpenSSL-Win64\bin)</p>

* **1ª Gera a chave PRIVADA** 

    *openssl genrsa -out chave_rsa.pem 2048*
    
* **2ª Gera a chave PÚBLICA a partir da privada**

    openssl rsa -pubout -in chave_rsa.pem -out chave_publica.key
    
* **3ª Gerar chave BEGIN PRIVATE KEY para usar no projeto**

    openssl pkcs8 -topk8 -inform PEM -in chave_rsa.pem -out chave_privada.pem -nocrypt
     
* **4ª Usando as chaves no projeto**

    Para usar as chaves no projeto é bem simples, vá até o arquivo chave_privada.pem e abra ele com um editor de texto (notepad++ por exemplo)
    Você irá ver um base64.
    Vale resaltar que você precisa colocar todo o base64 em apenas uma linha.
    copie e cole ele no arquivo application.yml na tag (secret-key).
    
    Faça a mesma coisa no arquivo chave_publica.key porem cole ele na tag (public-key).