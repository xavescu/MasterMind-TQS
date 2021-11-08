# MasterMind-TQS

El meu projecte consisteix en el desenvolupament del joc MasterMind. El projecte serà desenvolupat en Java.

# Regles de joc 
MasterMind és un joc on 2 jugadors es reparteixen els rols de "CodeMaker" i "CodeBraker". 

El "CodeMaker" serà responsable de crear un codi secret, una combinació de 6 colors (Blanc, Negre, Blau, Verd, Groc i Vermell). El "CodeBreaker" disposa de 10 torns per endevinar el codi. A cada torn, el "CodeBreaker" proposarà una solució. El "CodeMaker" donarà pistes per ajudar a resoldre el codi secret. Puntuarà amb un punt blanc per cada color que  es trobi en el codi secret pero no en la mateixa posició i un punt vermell si un color es troba en el codi i en la posició correcte.

Si el codi es desxifrat el "CodeMaker" guanya un punt per cada proposta de codi (com a max 10) realitzada pel "CodeBreaker". Si el codi no es desxifrat, el "CodeMaker" guanya 11 punts (10 + 1 extra).
El jugadors canvien els rols i es juga una nova partida. 

Guanya el jugador amb més punts després de 4 rondes.

# Funcionalitats del projecte 
El projecte consistirà en una app de consola desenvolupada en Java. La interficie serà del estil ASCII. El joc tindrà possibilitat de jugar 1 jugador contra la PC o 2 jugadors. Per al funcionament de la PC, aquesta sols intentarà colocar valors aleatoris en el codi proposat, si aconsegueix un punt vermell, mirarà de fixar una posició, intentant sempre mantenir el maxim de punts vermells.
