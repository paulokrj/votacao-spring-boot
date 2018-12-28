# votacao-spring-boot

Foi utilizado spring boot versão 2.0.7 e MySQL como base de dados para o objetivo.

O sistema conta com duas apis, Pauta Api e Voto Api:

# Pauta Api
* Serviço para cadastro de novas pautas
* Serviço para buscar informações basicas da pauta, filtrando por codigo
* Serviço que retorna o resultado dos votos, filtrando por codigo, o serviço retorna o total de votos, o total por opção e também porcentagem
* Serviço que inicia a sessão, onde é obrigatorio somente passar o codigo da pauta e é assumido tempo default de 1 min para a pauta
* Serviço que iniicia a sessão, sendo obrigatorio codigo da pauta e tempo em minutos da duração da pauta

# Voto Api
* Serviço para cadastrar novo vota para uma pauta
