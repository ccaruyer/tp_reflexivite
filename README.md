# POOA – TP Introspection et réflexivité
_(inspiré d'un énoncé de Yvan Royon)_

## 🎯 But du TP 
Il s'agit d'explorer les mécanismes d'introspection offerts par Java.
Pour cela, nous allons manipuler les classes `Class`, `Object`, 
ainsi que les éléments du package `java.lang.reflect`.

## 🎁 Introduction 

De retour d'un voyage dans un pays lointain, votre papi préféré vous ramène un très beau spécimen : 
une instance de `java.util.Vector` originale, peuplée d'un certain nombre d'éléments. Merci papi !

C'est très gentil de sa part, seulement il est un peu tête-en-l'air : il a oublié de vous dire ce qu'il y avait dans le Vector. 
Pire, il a perdu le code source et la javadoc du programme qui l'a généré. 
Et bien sûr, vous n'avez même pas le ticket de caisse pour vous faire rembourser.

Qu'allez-vous donc faire de ce Vector ? Vous pouvez récupérer le contenu sous forme d'objets (`java.lang.Object`), 
mais vous ne savez pas quoi en faire. Vous ne savez pas vers quelle classe effectuer le transtypage (« cast » en VO), comme vous faites d'habitude :

>  `String s = (String) monVector.get(1);`

Mais rassurez-vous, tout n'est pas perdu (ouf). En effet, Java vous fournit des mécanismes d'introspection et de réflexivité, 
respectivement pour découvrir la structure interne d'une classe ou d'un objet et pour agir dessus.

Vous allez dans ce TP découvrir la classe `java.lang.Class`, la classe `java.lang.Object` et le package `java.lang.reflect`. 
Il s'agit d'outils très puissants et « bas niveau » dans Java, qui rendent possibles des technologies comme RMI ou JavaBeans (cf. sérialisation). 
Vous les utiliserez pour tout découvrir sur le contenu du Vector offert par papi.

## 🎛 Préparation

Dans le dossier `src/`, vous trouverez la classe principale dans laquelle vous allez travailler : la classe `Main`. 
À l'intérieur, le code qui permet de récupérer le vecteur contenant les cadeaux est déjà écrit. 
Quant à la classe `PapiBarbu`, rien d'intéressant pour vous là-dedans.

Dans le dossier `libs/`, vous trouverez les fichiers jars nécessaires à l'exécution du projet, 
dont le fameux `cadocrypt.jar`, un fichier crypté contenant les cadeaux et généré grâce à un simulateur de papi top secret, 
ainsi qu'un jar qui permet de décrypter tout ça. 
Pour démarrer, il faut ajouter ces librairies au projet si ce n'est pas déjà fait. 

Ouvrez les propriétés du projet (`Project Structure`, ou `Module Settings`). 
Dans l'onglet `Libraries`, 
ajouter une librairie et y inclure le fichier `cadocrypt.jar` ainsi que le fichier `JarStarter-[votre_os].jar`

![](libs/img_lib.png)

Ensuite, dans l'onglet `Modules` > `Dependencies`, 
changer le scope de la librairie créée pour le mettre à `Runtime`.

![](libs/img_scope.png)

> **👊 POUR LA SUITE**
> 
> - Gardez la javadoc sous les yeux : [https://docs.oracle.com/...](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Class.html)
> - Modifiez la classe `Main` et testez/vérifiez vous-mêmes les réponses
> - Dialoguez avec l'enseignant : le but est de comprendre !
> - Ayez l'âme d'explorateur qui anime papi : soyez curieux ... 

## Classe `Object`

En Java, toutes les classes héritent de la classe `Object`. Ceci permet d'une part d'adresser tous les objets de manière générique, par exemple pour les placer dans un Vector, et d'autre part de forcer des comportements génériques à tous les objets. C'est le cas par exemple de la méthode `toString()` :

**Question 1 :** À quoi sert la méthode `toString()` de la classe `Object` ? Qu'est-elle censée nous renvoyer ?
> permet de récupérer un string 
> elle renvoie les attributs de l'objet sous forme de string


**Question 2 :** Qu'est-ce que cette méthode vous permet de déduire sur chacun des éléments qui sont dans le vecteur ?
> Elle permet de savoir le type
> on voit qu'elle contient deux objets et un string et une interface

**Question 3 :** Qu'en concluez-vous ?
> que l'objet

## Classe `Class` et package `java.lang.reflect`

La classe `java.lang.Class` fournit une représentation des classes. Nous verrons quelle est cette représentation plus loin. `Class` étant une classe, on peut l'instancier... 

**Question 4 :** Mais alors, les classes sont-elles des objets ?

> Indice : ǝᴉɓoๅouᴉɯɹǝʇ. Ne pas confondre la notion de classe et sa représentation interne dans la machine virtuelle. 
Cette représentation interne des classes permet de les introspecter (découvrir leur composition interne).
>Non

**Question 5 :** À quoi peut-on accéder ?

Repérez comment accéder à la liste des méthodes d'une classe, de ses constructeurs, de ses attributs. Les types retournés par ces méthodes appartiennent au package `java.lang.reflect`.

**Question 6 :** Quels sont les méthodes, constructeurs, attributs des éléments contenus dans le vecteur ? (Codez !)



> À titre d'exemple, choisissez un des éléments du vecteur. Découvrez dynamiquement sa liste de méthodes, et appelez une de ces méthodes. Rappel : lorsque vous codez, vous ne connaissez pas à l'avance le nom de la méthode à invoquer !
> 
> Comparez et testez les méthodes `getFields()` et `getDeclaredFields()`. Que retournent-elles ?
>

**Question 7 :** Que pouvez-vous dire au sujet de l'encapsulation (Pensez aux mots-clés `private`, `public`) ? Y a-t-il des incidences du point de vue sécurité ? Discutez !
>Pour l'encapsulation, on peut accéder aux attributs privés mais de manière masqué. 
>Et pour la sécurité, si les variables encapsulées sont dans une classe et que les autres classes ne peuvent pas y accéder directement. 
>Nous ne pouvons y accéder que via les méthodes de classe publique. De cette manière, nous pouvons masquer les données importantes et limiter leur modification.


**Question 8 :** D'après vous, à quoi ces méthodes peuvent servir dans la vraie vie ? Quelles sortes d'applications pourraient avoir besoin de ce genre de méthodes ?

> Dans la vraie vie, cette méthode permetterais de protéger l'accès aux données privées d'un individu ou d'une société.
> Tel que les données médicales ...

**Question 9 :** Pourquoi la classe `Class` est-elle `final` ?
> Car on ne peut pas la modifier
> Elle permet de créer nos classes
## Réfléxivité

Nous avons vu qu'il est possible de récupérer énormément d'informations sur les classes et les objets, y compris ceux que le développeur ne connaît pas à priori. Nous allons maintenant voir qu'il est possible d'agir dessus.

Observez la classe `java.lang.reflect.Field`. Les méthodes `getXX` permettent de lire les valeurs des champs, les méthodes `setXX` de les modifier.


**Question 10 :** Modifiez un attribut public quelconque d'un des éléments contenus dans le vecteur.

> Jusqu'ici, cela correspond à une utilisation normale : `monObjetDeClasseX.unAttributPublic = valeur;`
> 
> La différence est que la classe et le nom de l'attribut sont découverts dynamiquement, sans connaissance préalable.

Etape suivante : par héritage, la classe `Field` dispose d'une méthode `setAccessible()`.

**Question 11 :** Que fait cette méthode ?

**Question 12 :** Utilisez-la en reprenant vos conclusions aux questions 6, 7 et 10.

## 🚨 Conclusion

Si vous avez bien suivi, vous venez de modifier la valeur d'un champ private appartenant à une instance d'une classe que vous ne connaissez même pas et qui se trouvait dans un fichier jar encrypté. 🤨

Cela signifie que, lorsque vous utilisez Java dans un environnement critique (serveur d'entreprise...), il faut prendre des précautions supplémentaires. C'est également vrai pour .NET, et pour n'importe quelle technologie capable d'introspection et de réflexivité. En Java, on peut se protéger de modifications extérieures en utilisant un « Security Manager ».

**Question 13 :** Faites quelques recherches et implémentez un « Security Manager » basique qui empêche ces modifications.