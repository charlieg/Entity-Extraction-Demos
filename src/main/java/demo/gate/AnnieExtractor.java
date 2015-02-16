/*

Entity-Extraction-Demos
Copyright (C) 2015 Charlie Greenbacker

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this program.
If not, see <http://www.gnu.org/licenses/>.

=====================================================================

AnnieExtractor.java

*/

package demo.gate;

import gate.Annotation;
import gate.Corpus;
import gate.CorpusController;
import gate.Factory;
import gate.Gate;
import gate.util.persistence.PersistenceManager;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;

/**
 * A simple example of how to use GATE's ANNIE information extractor
 * to extract named entities from English text.
 */
public class AnnieExtractor {
    public static void main(String[] args) throws Exception {
        // initialize the GATE library
        Gate.runInSandbox(true);
        Gate.init();

        // load the default (English) ANNIE application from the saved state
        CorpusController annie = (CorpusController) PersistenceManager.loadObjectFromFile(
                new File("src/main/resources/plugins/ANNIE/ANNIE_with_defaults.gapp"));

        // create a GATE corpus containing a single document
        Corpus corpus = Factory.newCorpus("corpus");
        String text = "Steve works for Apple Inc in California.";
        corpus.add(Factory.newDocument(text));

        // tell the ANNIE application about the corpus and run it
        annie.setCorpus(corpus);
        annie.execute();

        // display the results (extracted entity + metadata from ANNIE)
        for (Annotation entity : corpus.get(0).getAnnotations().get(new HashSet<String>(
                Arrays.asList("Person", "Location", "Gpe", "Organization")))) {
            System.out.println(text.substring(entity.getStartNode().getOffset().intValue(),
                    entity.getEndNode().getOffset().intValue()));
            System.out.println(entity.toString());
        }

        /* If everything works properly, your output should look like this:

            Steve
            AnnotationImpl: id=35; type=Person; features={gender=male, kind=firstName, rule=GazPersonFirst, firstName=Steve, ruleFinal=PersonFinal}; start=NodeImpl: id=0; offset=0; end=NodeImpl: id=1; offset=5

            Apple Inc
            AnnotationImpl: id=36; type=Organization; features={orgType=unknown, rule=OrgXEnding, ruleFinal=OrgFinal}; start=NodeImpl: id=6; offset=16; end=NodeImpl: id=9; offset=25

            California
            AnnotationImpl: id=37; type=Location; features={kind=locName, rule=InLoc1, locType=province, ruleFinal=LocFinal}; start=NodeImpl: id=12; offset=29; end=NodeImpl: id=13; offset=39

         */
    }
}
