# Sports competition project

# **Authors**

 - Manal Laghmich
 - Reda Id-taleb

# **Introduction**

The project concerns the creation of an application allowing to organize a sports competition and to make it play.

Sports competitions are characterized by a set of matches that are played between different competitors.

A competition allows its matches to be played until only one winner remains, designated as a winner.

We consider that there are 3 types of sports competitions:

  * The championships (League): are played in round-trip matches
  
  * The tournaments (Tournament): are played in several rounds, -at each round- only the winners who have won their matches participate in the next round, until only one winner remains.

  * The masters (Master): are played in 2 phases, the 1st phase contains hens each hen plays a League. The winners of this phase are selected to play the second which is a Tournament.

These Competitions are played with a type of match where the players are taken randomly.

So there's bound to be a winner in every game.

# **Documentation Generation**
* To generate the documentation, Go to the competition/src($ cd competition/src) folder and issue the following command:
  ```bash
  $ javadoc -d ../docs -subpackages competition
  ```
 To view it, open the index.html file located in the docs folder.

 The documentations are stored in the /docs folder located at the root.

# **Compilation of project sources (to be done first)**

* To compile the project sources, go to the root of the project and issue the following commands:
  $ cd competition

  ```bash
  $mkdir classes
  ```
  Then put yourself in the src($ cd src) folder and issue the following command:
  ```bash
  $ javac competition/*.java -d ../classes
  ```

# **Compiling and running tests**

* To compile all the test classes, go to the root of the folder (where there is src and test folder), ie in the competition folder
and run the following command:

  ```bash
  $ javac -classpath test-1.7.jar -d ./classes test/competition_sportive/*.java
  ```

* Abstract test classes cannot be executed by junit4 (their executions return errors), this is the case of CompetitionTest - MatchTest - SelectionTest

* To run a test class, stay in the root
and run the following command:

  ```bash
  $ java -jar test-1.7.jar sport_competition.TestClassName
  ```
  * example: To run the LeagueTest class, run the command:
  ```bash
  $ java -jar test-1.7.jar competition_sportive.LeagueTest
  ```

# **Execution of the program with the .jar executable**

  cd into the competition/classes folder and then issue the following command:

  ```bash
  $ jar cvfm ../competition.jar ../MANIFEST.MF *
  ```

Then go to the competition folder (still at the root) and issue the following command:
  ```bash
$ java -jar competition.jar
  ```

# **Execution of the program without the .jar executable**
go to the competition folder (at the root) and then issue the following command:
  ```bash
  $ java -classpath classes competition.Main
  ```