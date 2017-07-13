package org.di.uminho.cguide.wizard.Edit.Formula;

import org.apache.log4j.Logger;
import org.protege.editor.core.ui.wizard.Wizard;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.ui.ontology.wizard.create.OntologyIDPanel;

public class EditFormulaWizard extends Wizard {

	private static final Logger logger = Logger.getLogger(EditFormulaWizard.class);

	public EditFormulaPage2 editformulapage2;

	public EditFormulaPage3 editformulapage3;

	public EditFormulaPage4 editformulapage4;

	private OntologyIDPanel IDPanel;

	private OWLModelManager owlModelManager;

	public EditFormulaWizard(OWLEditorKit editorKit, String formula_individual) {
		setTitle("Formula Editor Wizard");
		this.owlModelManager = editorKit.getModelManager();

		registerWizardPanel(EditFormulaPage2.ID,
				editformulapage2 = new EditFormulaPage2(editorKit, formula_individual));

		registerWizardPanel(EditFormulaPage3.ID,
				editformulapage3 = new EditFormulaPage3(editorKit, formula_individual));

		registerWizardPanel(EditFormulaPage4.ID,
				editformulapage4 = new EditFormulaPage4(editorKit, formula_individual));

		setCurrentPanel(EditFormulaPage2.ID);
	}

}
