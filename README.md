# Graphing-Calculator-Graphics

![Alt text](/GraphingCalculator/builds/iconPrev.png?raw=true "Grafit Icon")

Grafit! Graphing Calculator 2019 ©
by Mr. Hales' Precalculus Honors Class, 2019-20

https://github.com/Seb-Park/Graphing-Calculator-Graphics/

Contents
--------
1. Introduction
2. Acknowledgements
3. Features
4. How To Use
5. License
6. Known Issues

Introduction
------------

This is a graphing calculator program that can take a user-inputed polynomial and place it on a coordinate system with essential points (zeroes, y-intercepts, and vertices). We made this program to learn about how computers can take polynomials and find the essential points differently than a human would.

Acknowledgements
----------------
Thank you to:
* Our Teacher, Mr. Chris Hales
* Tori Choo (https://github.com/ToriChoo)
* Thea Chung (https://github.com/TChung21)
* Timothy Colledge (https://github.com/TimmColl03)
* Oliver Eielson
* Kathryn Fernandopulle (https://github.com/kfernandopulle21)
* Madeleine Fitzgibbon
* Sebastian Park (https://github.com/Seb-Park)
* Zachary Rahaman (https://github.com/ramennoodle5)
* Florance Wu (https://github.com/Florance39)
 
Features
--------
  * Graphing standard form polynomials on cartesian coordinate system
  * X-intercepts
  * Y-intercepts
  * Local Minimums
  * Zooming in and out via keyboard or scrolling
  * Readjusting view via arrow keys
  * Clickable points

How To Use
----------

If you have a Mac, you can open the build folder and run the Grafit!.app application and it will run a jar file as a native Mac app. If you are running Windows or Linux, you can also run the Jar separately in the GraphingCalculator/out/artifacts/GraphingCalculator_jar/ folder. The user can also run the "Main.java" class through command prompt or through a text editor. The app is fairly light; it is only 1MB in file size.

Once you have run the program, you can type in an equation in the text field at the bottom. 
  * The polynomial must be in standard form. 
  * The exponents are expressed through the "^" character (or Shift+6)
  * Parenthesis are not yet working correctly
  * Instead of subtracting a term, the user must add a negative term (e.g. 6x^2-3x will not work; instead use 6x^2+-3x)
  * Constant terms (where x is raised to a power of 0) must be expressed as terms multiplied by x^0 (e.g. 2x+3x^0)
  * Coefficients of one must be written out (e.g. 1x^2+1x)

License
-------
This project is licensed under the MIT License, which can be found in License.txt in the root directory.

Source code:
https://github.com/Seb-Park/Graphing-Calculator-Graphics/

Known issues
------------------------
~~Sometimes the program does not find all zeroes or local minimums depending on the computer. We are working to fix this bug.~~
Zooming into the graph too much will cause the graph to appear choppy as the graph is graphed according to pixel values. It does also not yet find inflection points for polynomials with an odd highest exponent.
