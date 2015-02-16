# Entity-Extraction-Demos
A collection of example code for performing entity extraction (i.e., [named entity recognition](http://en.wikipedia.org/wiki/Named-entity_recognition)) in a variety of human languages using a variety of Java-based open source software libraries.

## How to Use:

This project will eventually contain entity extraction examples for several different NER tools. Each example or tool will have some particular instructions for getting it up & running. Please follow these instructions carefully to ensure you are able to run this code successfully.

Additionally, the `pom.xml` file contains Maven dependencies for each individual NER tool. When using them in your own projects, you will obviously only need to include the dependencies for the tools you're actually using, not all of them.

## Instructions for GATE:

Since the creators of [GATE](https://gate.ac.uk/) don't distribute their plugins bundled with the JARs published on [Maven Central](http://search.maven.org/#browse%7C-2026874896), you'll need to download them separately before running the example code.

#### To download GATE plugins:

1. Go to [https://gate.ac.uk/download/](https://gate.ac.uk/download/)

1. Download either the `BIN`, `SRC`, or `ALL` package... or just download this package directly: [https://sourceforge.net/projects/gate/files/gate/8.0/gate-8.0-build4825-SRC.zip/download](https://sourceforge.net/projects/gate/files/gate/8.0/gate-8.0-build4825-SRC.zip/download)

1. Unzip the package, and copy the `plugins` directory it contains into `gate-demo/src/main/resources`

`AnnieExtractor.java` demonstrates how to use the default English entity extractor for GATE (called ANNIE), and `ArabicExtractor.java` provides another example using the Arabic version.

## License:

*Note: Since certain third-party libraries used in this project are licensed via the GPL, this project as a whole must be released under the GPL as well. However, individual files will be marked with more permissive open source licenses whenever possible. For example, the GATE examples are released under the LGPL.*

Entity-Extraction-Demos

Copyright (C) 2015 Charlie Greenbacker

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
