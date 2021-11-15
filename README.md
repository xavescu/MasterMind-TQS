# MasterMind-TQS

El meu projecte consisteix en el desenvolupament del joc MasterMind. El projecte serà desenvolupat en Java.

# Regles de joc 
MasterMind és un joc on 2 jugadors es reparteixen els rols de "CodeMaker" i "CodeBraker". 

El "CodeMaker" serà responsable de crear un codi secret, una combinació de 6 colors (Blanc, Negre, Blau, Verd, Groc i Vermell). El "CodeBreaker" disposa de 10 torns per endevinar el codi. A cada torn, el "CodeBreaker" proposarà una solució. El "CodeMaker" donarà pistes per ajudar a resoldre el codi secret. Puntuarà amb un punt blanc per cada color que  es trobi en el codi secret pero no en la mateixa posició i un punt vermell si un color es troba en el codi i en la posició correcte.

Si el codi es desxifrat el "CodeMaker" guanya un punt per cada proposta de codi (com a max 10) realitzada pel "CodeBreaker". Si el codi no es desxifrat, el "CodeMaker" guanya 11 punts (10 + 1 extra).
El jugadors canvien els rols i es juga una nova partida. 

Guanya el jugador amb més punts després de 4 rondes.

# Funcionalitats del projecte 
El projecte consisteix en una app de consola desenvolupada en Java. La interfície serà del estil ASCII. El joc tindrà possibilitat de jugar 1 jugador contra la PC o 2 jugadors. Si l’usuari decideix jugar contra la CPU haurà de intentar endevinar tants SecretCodes com sigui possible. Si es capaç d’endevinar el codi en 10 intents se li sumarà la puntuació i un nou codi serà generat. Si gasta els 10 intents, si la seva puntació és la nova millor serà guardada a BD (un JSON) per ser mostrada al inici de la APP.
En el mode de 2 jugadors es repartiran els rols de CODE_BREAKER i CODE_MAKER. Després de 4 rondes guanyarà el jugador amb major puntuació.
