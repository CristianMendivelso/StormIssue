Experimento Contador de Palabras de doble Entrada de datos 
================
Este es un repositorio que se utilizará para un proyecto de investigación de la **Escuela Colombiana de Ingeniería Julio Garavito**.

Este experimento tiene como objetivo replicar y posteriormente solucionar un Issue de Apache Storm utilizando la herramienta [Eketal](https://github.com/unicesi/eketal "Eketal").

El [Issue](https://issues.apache.org/jira/browse/STORM-284 "Issue") seleccionado corresponde a un problema de *starvation*

La instrumentación con Eketal actual intercepta el método execute(Tuple) de la clase TweetSplitterBolt e imprime 

------------------Reaction detected with Eketal--------------------------

por consola, interceptar este método es importante, ya que gran parte de la solución propuesta tiene que ver con este método.

Actualmente este repositorio esta hecho para ser corrido en modo local, y así facilitar las pruebas

Este repositorio contiene todo lo relacionado al proyecto: 
Experimento Contador de Palabras de doble Entrada de datos Utilizando la Herramienta **Apache Storm** Basado en https://github.com/jalonsoramos/storm-word-count y https://github.com/khajaasmath786/StormTutorial y utlizando las caracterísitcas de Streaming de los mismos.
# StormIssue
