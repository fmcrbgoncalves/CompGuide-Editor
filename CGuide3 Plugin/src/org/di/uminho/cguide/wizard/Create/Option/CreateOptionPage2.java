package org.di.uminho.cguide.wizard.Create.Option;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.di.uminho.cguide.wizard.Create.SelectClassPage;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.ui.AbstractOWLWizardPanel;

public class CreateOptionPage2 extends AbstractOWLWizardPanel {

	public static final String ID = "CreateOptionPage2";

	public static final String title = "Option values";

	public int option_type = 0;
	public JTextField numericalvalue_textField;
	public JTextField qualitativevalue_textField;
	public JTextField parameteridentifier_textField;
	public JTextField optionparameter_textField;
	public JTextField unit_textField;

	public JRadioButton numericalvalue_RadioButton;

	public JRadioButton qualitativevalue_RadioButton;

	public CreateOptionPage2(OWLEditorKit owlEditorKit) {
		super(ID, title, owlEditorKit);
	}

	protected void createUI(JComponent parent) {
		setInstructions("Please insert data required for the creation of the associated Option.");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 111, 86, 0 };
		gridBagLayout.rowHeights = new int[] { 23, 23, 20, 20, 20, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		parent.setLayout(gridBagLayout);

		JRadioButton numericalvalue_RadioButton = new JRadioButton("Numerical Value:", true);
		GridBagConstraints gbc_numericalvalue_RadioButton = new GridBagConstraints();
		gbc_numericalvalue_RadioButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_numericalvalue_RadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_numericalvalue_RadioButton.gridx = 0;
		gbc_numericalvalue_RadioButton.gridy = 0;
		parent.add(numericalvalue_RadioButton, gbc_numericalvalue_RadioButton);

		numericalvalue_textField = new JTextField();
		numericalvalue_textField.setColumns(10);
		numericalvalue_textField.setEnabled(true);
		numericalvalue_textField.setToolTipText("Enter Option Numerical Value");
		GridBagConstraints gbc_numericalvalue_textField = new GridBagConstraints();
		gbc_numericalvalue_textField.anchor = GridBagConstraints.WEST;
		gbc_numericalvalue_textField.insets = new Insets(0, 0, 5, 0);
		gbc_numericalvalue_textField.gridx = 1;
		gbc_numericalvalue_textField.gridy = 0;
		parent.add(numericalvalue_textField, gbc_numericalvalue_textField);

		JRadioButton qualitativevalue_RadioButton = new JRadioButton("Qualitative Value:");
		GridBagConstraints gbc_qualitativevalue_RadioButton = new GridBagConstraints();
		gbc_qualitativevalue_RadioButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_qualitativevalue_RadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_qualitativevalue_RadioButton.gridx = 0;
		gbc_qualitativevalue_RadioButton.gridy = 1;
		parent.add(qualitativevalue_RadioButton, gbc_qualitativevalue_RadioButton);

		qualitativevalue_textField = new JTextField();
		qualitativevalue_textField.setColumns(10);
		qualitativevalue_textField.setEnabled(false);
		qualitativevalue_textField.setToolTipText("Enter Option Qualitative Value");
		GridBagConstraints gbc_qualitativevalue_textField = new GridBagConstraints();
		gbc_qualitativevalue_textField.anchor = GridBagConstraints.WEST;
		gbc_qualitativevalue_textField.insets = new Insets(0, 0, 5, 0);
		gbc_qualitativevalue_textField.gridx = 1;
		gbc_qualitativevalue_textField.gridy = 1;
		parent.add(qualitativevalue_textField, gbc_qualitativevalue_textField);

		JLabel parameteridentifier_label = new JLabel("Parameter Identifier:");
		GridBagConstraints gbc_parameteridentifier_label = new GridBagConstraints();
		gbc_parameteridentifier_label.anchor = GridBagConstraints.EAST;
		gbc_parameteridentifier_label.insets = new Insets(0, 0, 5, 5);
		gbc_parameteridentifier_label.gridx = 0;
		gbc_parameteridentifier_label.gridy = 2;
		parent.add(parameteridentifier_label, gbc_parameteridentifier_label);

		parameteridentifier_textField = new JTextField();
		parameteridentifier_textField.setColumns(10);
		GridBagConstraints gbc_parameteridentifier_textField = new GridBagConstraints();
		gbc_parameteridentifier_textField.anchor = GridBagConstraints.NORTHWEST;
		gbc_parameteridentifier_textField.insets = new Insets(0, 0, 5, 0);
		gbc_parameteridentifier_textField.gridx = 1;
		gbc_parameteridentifier_textField.gridy = 2;
		parent.add(parameteridentifier_textField, gbc_parameteridentifier_textField);

		JLabel optionparameter_label = new JLabel("Option Parameter:");
		GridBagConstraints gbc_optionparameter_label = new GridBagConstraints();
		gbc_optionparameter_label.anchor = GridBagConstraints.EAST;
		gbc_optionparameter_label.insets = new Insets(0, 0, 5, 5);
		gbc_optionparameter_label.gridx = 0;
		gbc_optionparameter_label.gridy = 3;
		parent.add(optionparameter_label, gbc_optionparameter_label);

		optionparameter_textField = new JTextField();
		optionparameter_textField.setColumns(10);
		GridBagConstraints gbc_optionparameter_textField = new GridBagConstraints();
		gbc_optionparameter_textField.anchor = GridBagConstraints.NORTHWEST;
		gbc_optionparameter_textField.insets = new Insets(0, 0, 5, 0);
		gbc_optionparameter_textField.gridx = 1;
		gbc_optionparameter_textField.gridy = 3;
		parent.add(optionparameter_textField, gbc_optionparameter_textField);

		JLabel unit_label = new JLabel("Unit:");
		GridBagConstraints gbc_unit_label = new GridBagConstraints();
		gbc_unit_label.anchor = GridBagConstraints.EAST;
		gbc_unit_label.insets = new Insets(0, 0, 0, 5);
		gbc_unit_label.gridx = 0;
		gbc_unit_label.gridy = 4;
		parent.add(unit_label, gbc_unit_label);

		unit_textField = new JTextField();
		unit_textField.setColumns(10);
		GridBagConstraints gbc_unit_textField = new GridBagConstraints();
		gbc_unit_textField.anchor = GridBagConstraints.NORTHWEST;
		gbc_unit_textField.gridx = 1;
		gbc_unit_textField.gridy = 4;
		parent.add(unit_textField, gbc_unit_textField);

		ButtonGroup buttongroup = new ButtonGroup();
		buttongroup.add(numericalvalue_RadioButton);
		buttongroup.add(qualitativevalue_RadioButton);

		// Add Listener
		numericalvalue_RadioButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// Option NumericalValue -> type 0
					option_type = 0;
					numericalvalue_textField.setEnabled(true);
					qualitativevalue_textField.setEnabled(false);
					qualitativevalue_textField.setText("");
				}
			}
		});

		qualitativevalue_RadioButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					// Option QualitativeValue -> type 1
					option_type = 1;
					numericalvalue_textField.setEnabled(false);
					qualitativevalue_textField.setEnabled(true);
					numericalvalue_textField.setText("");
				}
			}
		});
	}

	public Object getBackPanelDescriptor() {
		return SelectClassPage.ID;
	}

	public Object getNextPanelDescriptor() {
		return CreateOptionPage3.ID;
	}

}
