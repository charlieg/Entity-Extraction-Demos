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

ArabicExtractor.java

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
 * to extract named entities from Arabic text.
 */
public class ArabicExtractor {
    public static void main(String[] args) throws Exception {
        // initialize the GATE library
        Gate.runInSandbox(true);
        Gate.init();

        // load the Arabic ANNIE application from the saved state
        CorpusController annie = (CorpusController) PersistenceManager.loadObjectFromFile(
                new File("src/main/resources/plugins/Lang_Arabic/resources/arabic.gapp"));

        // create a GATE corpus containing a single document
        Corpus corpus = Factory.newCorpus("corpus");
        /* The following sentence comes from the Arabic Wikipedia article for Yasser Arafat
           (http://ar.wikipedia.org/wiki/%D9%8A%D8%A7%D8%B3%D8%B1_%D8%B9%D8%B1%D9%81%D8%A7%D8%AA).
           Google Translate tells us it means something like "At the end of 2004,
           Yasser Arafat's illness after two years of siege in his headquarters
           in Ramallah by the Israeli army, and went into a coma." in English.
         */
        String text = "وفي نهايات عام 2004 مرض ياسر عرفات بعد سنتين من حصاره داخل مقره في رام الله من قبل الجيش الإسرائيلي، ودخل في غيبوبة.";
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

                        ياسر عرفات
            AnnotationImpl: id=65; type=Person; features={kind=inferred, rule=PersonInferred}; start=NodeImpl: id=10; offset=24; end=NodeImpl: id=13; offset=34

            رام الله
            AnnotationImpl: id=67; type=Location; features={kind=inferred, rule=LocInferred}; start=NodeImpl: id=28; offset=67; end=NodeImpl: id=31; offset=75

            الجيش الإسرائيلي
            AnnotationImpl: id=68; type=Organization; features={kind=inferred, rule=OrgInferred}; start=NodeImpl: id=36; offset=83; end=NodeImpl: id=39; offset=99

         */
    }
}
