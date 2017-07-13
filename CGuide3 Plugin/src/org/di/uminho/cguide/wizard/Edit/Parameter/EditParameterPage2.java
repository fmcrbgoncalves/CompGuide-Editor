package org.di.uminho.cguide.wizard.Edit.Parameter;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Map;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.protege.editor.core.ui.wizard.WizardPanel;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.AbstractOWLWizardPanel;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

public class EditParameterPage2 extends AbstractOWLWizardPanel {

	public static final String ID = "EditParameterPage2";

	public static final String title = "Question Parameter Values";

	public JTextField possiblevalue_textField;
	public JTextField variablename_textField;
	public JTextField parameteridentifier_textField;
	public JTextField questionparameter_textField;
	public JTextField unit_textField;
	public JTextArea parameterdescription_textArea;

	private String parameter_individual = new String(), possibleValue = new String(), variableName = new String(),
			parameterIdentifier = new String(), questionParameter = new String(), unit = new String(),
			parameterDescription = new String();

	public EditParameterPage2(OWLEditorKit owlEditorKit, String parameter_individual) {
		super(ID, title, owlEditorKit);
		getParameter(parameter_individual);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please insert the Parameter Values of the Question Task.");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		parent.setLayout(gridBagLayout);

		JLabel possiblevalue_label = new JLabel("Possible Value:");
		GridBagConstraints gbc_possiblevalue_label = new GridBagConstraints();
		gbc_possiblevalue_label.insets = new Insets(0, 0, 5, 5);
		gbc_possiblevalue_label.gridx = 0;
		gbc_possiblevalue_label.gridy = 0;
		parent.add(possiblevalue_label, gbc_possiblevalue_label);

		possiblevalue_textField = new JTextField(this.possibleValue);
		GridBagConstraints gbc_possiblevalue_textField = new GridBagConstraints();
		gbc_possiblevalue_textField.insets = new Insets(0, 0, 5, 0);
		gbc_possiblevalue_textField.anchor = GridBagConstraints.WEST;
		gbc_possiblevalue_textField.gridx = 1;
		gbc_possiblevalue_textField.gridy = 0;
		parent.add(possiblevalue_textField, gbc_possiblevalue_textField);
		possiblevalue_textField.setColumns(10);

		JLabel variablename_label = new JLabel("Variable Name:");
		GridBagConstraints gbc_variablename_label = new GridBagConstraints();
		gbc_variablename_label.insets = new Insets(0, 0, 5, 5);
		gbc_variablename_label.gridx = 0;
		gbc_variablename_label.gridy = 1;
		parent.add(variablename_label, gbc_variablename_label);

		variablename_textField = new JTextField(this.variableName);
		variablename_textField.setColumns(10);
		GridBagConstraints gbc_variablename_textField = new GridBagConstraints();
		gbc_variablename_textField.insets = new Insets(0, 0, 5, 0);
		gbc_variablename_textField.anchor = GridBagConstraints.WEST;
		gbc_variablename_textField.gridx = 1;
		gbc_variablename_textField.gridy = 1;
		parent.add(variablename_textField, gbc_variablename_textField);

		JLabel parameteridentifier_label = new JLabel("Parameter Identifier:");
		GridBagConstraints gbc_parameteridentifier_label = new GridBagConstraints();
		gbc_parameteridentifier_label.anchor = GridBagConstraints.EAST;
		gbc_parameteridentifier_label.insets = new Insets(0, 0, 5, 5);
		gbc_parameteridentifier_label.gridx = 0;
		gbc_parameteridentifier_label.gridy = 2;
		parent.add(parameteridentifier_label, gbc_parameteridentifier_label);

		parameteridentifier_textField = new JTextField(this.parameterIdentifier);
		GridBagConstraints gbc_parameteridentifier_textField = new GridBagConstraints();
		gbc_parameteridentifier_textField.insets = new Insets(0, 0, 5, 0);
		gbc_parameteridentifier_textField.anchor = GridBagConstraints.WEST;
		gbc_parameteridentifier_textField.gridx = 1;
		gbc_parameteridentifier_textField.gridy = 2;
		parent.add(parameteridentifier_textField, gbc_parameteridentifier_textField);
		parameteridentifier_textField.setColumns(10);

		JLabel questionparameter_label = new JLabel("Question Parameter:");
		GridBagConstraints gbc_questionparameter_label = new GridBagConstraints();
		gbc_questionparameter_label.anchor = GridBagConstraints.EAST;
		gbc_questionparameter_label.insets = new Insets(0, 0, 5, 5);
		gbc_questionparameter_label.gridx = 0;
		gbc_questionparameter_label.gridy = 3;
		parent.add(questionparameter_label, gbc_questionparameter_label);

		questionparameter_textField = new JTextField();
		GridBagConstraints gbc_questionparameter_textField = new GridBagConstraints();
		gbc_questionparameter_textField.insets = new Insets(0, 0, 5, 0);
		gbc_questionparameter_textField.anchor = GridBagConstraints.WEST;
		gbc_questionparameter_textField.gridx = 1;
		gbc_questionparameter_textField.gridy = 3;
		parent.add(questionparameter_textField, gbc_questionparameter_textField);
		questionparameter_textField.setColumns(10);

		JLabel unit_label = new JLabel("Unit:");
		GridBagConstraints gbc_unit_label = new GridBagConstraints();
		gbc_unit_label.insets = new Insets(0, 0, 5, 5);
		gbc_unit_label.gridx = 0;
		gbc_unit_label.gridy = 4;
		parent.add(unit_label, gbc_unit_label);

		unit_textField = new JTextField();
		GridBagConstraints gbc_unit_textField = new GridBagConstraints();
		gbc_unit_textField.insets = new Insets(0, 0, 5, 0);
		gbc_unit_textField.anchor = GridBagConstraints.WEST;
		gbc_unit_textField.gridx = 1;
		gbc_unit_textField.gridy = 4;
		parent.add(unit_textField, gbc_unit_textField);
		unit_textField.setColumns(10);

		JLabel parameterdescription_label = new JLabel("Parameter Description:");
		GridBagConstraints gbc_parameterdescription_label = new GridBagConstraints();
		gbc_parameterdescription_label.insets = new Insets(0, 0, 0, 5);
		gbc_parameterdescription_label.anchor = GridBagConstraints.NORTHEAST;
		gbc_parameterdescription_label.gridx = 0;
		gbc_parameterdescription_label.gridy = 5;
		parent.add(parameterdescription_label, gbc_parameterdescription_label);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 5;
		parent.add(scrollPane, gbc_scrollPane);

		parameterdescription_textArea = new JTextArea(3, 20);
		scrollPane.setViewportView(parameterdescription_textArea);

	}

	@Override
	public void aboutToDisplayPanel() {

		possiblevalue_textField.setText(this.possibleValue);
		variablename_textField.setText(this.variableName);
		parameteridentifier_textField.setText(this.parameterIdentifier);
		questionparameter_textField.setText(this.questionParameter);
		unit_textField.setText(this.unit);
		parameterdescription_textArea.setText(this.parameterDescription);
		// TODO Auto-generated method stub
		super.aboutToDisplayPanel();
	}

	public Object getNextPanelDescriptor() {
		return WizardPanel.FINISH;
	}

	public void getParameter(String parameter_individual_name) {

		OWLNamedIndividual individual = getOWLModelManager().getOWLDataFactory().getOWLNamedIndividual(
				IRI.create(getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI() + "#"
						+ parameter_individual_name));

		// Get Data Properties of each Parameter
		Map<OWLDataPropertyExpression, Set<OWLLiteral>> data = individual
				.getDataPropertyValues(getOWLModelManager().getActiveOntology());
		for (Map.Entry<OWLDataPropertyExpression, Set<OWLLiteral>> entry : data.entrySet()) {
			try {

				if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("possibleValue")) {
					this.possibleValue = entry.getValue().iterator().next().getLiteral();
				}
				if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("variableName")) {
					this.variableName = entry.getValue().iterator().next().getLiteral();
				}
				if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("parameterIdentifier")) {
					this.parameterIdentifier = entry.getValue().iterator().next().getLiteral();
				}
				if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("questionParameter")) {
					this.questionParameter = entry.getValue().iterator().next().getLiteral();
				}
				if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("unit")) {
					this.unit = entry.getValue().iterator().next().getLiteral();
				}
				if (entry.getKey().asOWLDataProperty().getIRI().getFragment().equals("parameterDescription")) {
					this.parameterDescription = entry.getValue().iterator().next().getLiteral();
				}
			} catch (Exception e) {
			}
		}
	}

}
