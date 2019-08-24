
/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package application;
// Imports
///////////////
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDFS;

/**
 * <p>TODO class comment</p>
 */
public class PizzaSparqlNoInf extends Base
{
    /***********************************/
    /* Constants                       */
    /***********************************/

    // Directory where we've stored the local data files, such as pizza.rdf.owl
    public static final String SOURCE = "./src/application/";

    // Pizza ontology namespace
    public static final String PIZZA_NS = "http://www.co-ode.org/ontologies/pizza/pizza.owl#";

    /***********************************/
    /* Static variables                */
    /***********************************/

    @SuppressWarnings( value = "unused" )
    private static final Logger log = LoggerFactory.getLogger( PizzaSparqlNoInf.class );

    /***********************************/
    /* Instance variables              */
    /***********************************/

    /***********************************/
    /* Constructors                    */
    /***********************************/

    /***********************************/
    /* External signature methods      */
    /***********************************/

    /**
     * @param args
     */
    public static void main( String[] args ) {
        new PizzaSparqlNoInf().setArgs( args ).run();
    }

    public void run() {
        OntModel m = getModel();
        loadData( m );
        String prefix = "prefix pizza: <" + PIZZA_NS + ">\n" +
                        "prefix rdfs: <" + RDFS.getURI() + ">\n" +
                        "prefix owl: <" + OWL.getURI() + ">\n";
        
        System.out.println(prefix);


        showQuery( m,
                   prefix +
                   "select ?pizza ?y where {?pizza a owl:Class ; " +
                   "                            rdfs:subClassOf ?restriction.\n" +
                   "                     ?restriction owl:onProperty pizza:hasTopping  ;" +
                   "                            owl:someValuesFrom ?y" +
                   "}" );
    }

    protected OntModel getModel() {
        return ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
    }

    protected void loadData( Model m ) {
        FileManager.get().readModel( m, SOURCE + "pizza.owl.rdf" );
    }

    protected void showQuery( Model m, String q ) {
        Query query = QueryFactory.create( q );
        QueryExecution qexec = QueryExecutionFactory.create( query, m );
        try {
            ResultSet results = qexec.execSelect();
           // ResultSetFormatter.out( results, m );
            Map resultat = getResultAsMap(results);
            
            
            while (results.hasNext()) {
    			System.out.println(results.next().get("pizza").asResource().getURI());
    		}
            
        }
        finally {
            qexec.close();
        }

    }
    
    public Map<String, Integer> getResultAsMap(ResultSet rs) {
        Map<String, Integer> myMap = new HashMap<String, Integer>();
        int i = 0;
        for (; rs.hasNext();) {
            QuerySolution soln = rs.nextSolution();

            String pizza = soln.get("pizza").toString();
            System.out.println("La pizza = "+pizza);
            String y = soln.get("y").toString();
            i++;
            myMap.put(pizza, i);
        }
        return myMap;
    }
    
    
    /***********************************/
    /* Internal implementation methods */
    /***********************************/

    /***********************************/
    /* Inner class definitions         */
    /***********************************/

}