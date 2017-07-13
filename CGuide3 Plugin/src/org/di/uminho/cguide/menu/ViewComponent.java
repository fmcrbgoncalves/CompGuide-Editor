/**
 * 
 */
package org.di.uminho.cguide.menu;

/**
 * @author filipe
 *
 */
import java.awt.BorderLayout;

import org.apache.log4j.Logger;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.model.OWLWorkspace;
import org.protege.editor.owl.ui.view.AbstractOWLViewComponent;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntologyManager;

//import org.eclipse.jgit.api.*;
//import org.eclipse.jgit.api.errors.*;
//import org.eclipse.jgit.api.CreateBranchCommand.SetupUpstreamMode;
//import org.eclipse.jgit.internal.storage.file.FileRepository;
//import org.eclipse.jgit.lib.Repository;

public class ViewComponent extends AbstractOWLViewComponent {
	private static final long serialVersionUID = -4515710047558710080L;

	private OWLEditorKit editorkit;
	private OWLModelManager manager;
	private OWLOntologyManager ontology_manager;
	private OWLDataFactory factory;
	private OWLWorkspace workspace;
	private IRI ontologyIRI;

	private static final Logger log = Logger.getLogger(ViewComponent.class);

	private Metrics metricsComponent;

	@Override
	protected void initialiseOWLView() throws Exception {
		setLayout(new BorderLayout());

		// loadontology();

		// Prepares variables to work with
		manager = getOWLModelManager();
		editorkit = getOWLEditorKit();
		ontology_manager = manager.getOWLOntologyManager();
		factory = getOWLDataFactory();
		workspace = getOWLWorkspace();
		ontologyIRI = manager.getActiveOntology().getOntologyID().getOntologyIRI();

		metricsComponent = new Metrics(editorkit, manager, ontology_manager, factory, workspace, ontologyIRI);
		add(metricsComponent, BorderLayout.CENTER);
		log.info("Example View Component initialized");

		// createCPG();
	}

	@Override
	protected void disposeOWLView() {
		metricsComponent.dispose();
	}

	/*
	 * private void loadontology() {
	 * 
	 * // Loading the ontology try { // Prepares variables to work with manager
	 * = getOWLModelManager(); editorkit = getOWLEditorKit(); ontology_manager =
	 * manager.getOWLOntologyManager();
	 * 
	 * // File owlfile = new File("Cguide3.owl"); File owlfile = new
	 * File("Cguide.owl");
	 * 
	 * // Load CGuide Ontology and set it as Active Ontology
	 * manager.setActiveOntology(ontology_manager.
	 * loadOntologyFromOntologyDocument(owlfile));
	 * manager.fireEvent(EventType.ONTOLOGY_LOADED);
	 * 
	 * factory = getOWLDataFactory(); workspace = getOWLWorkspace();
	 * 
	 * // Obtain Ontology IRI //
	 * http://www.semanticweb.org/ontologies/2012/3/CompGuide.owl ontologyIRI =
	 * manager.getActiveOntology().getOntologyID().getOntologyIRI();
	 * 
	 * System.out.println("Loaded CGuide ontology!");
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * System.out.println("Ontology not found!"); System.out.
	 * println("CGuide OWL file must be located in the Protégé instalation folder."
	 * ); } }
	 */

}