/*
 * Copyright: Copyright 2010 Topic Maps Lab, University of Leipzig. http://www.topicmapslab.de/    
 * License:   Apache License, Version 2.0 http://www.apache.org/licenses/LICENSE-2.0.html
 * 
 * @author Sven Krosse
 * @email krosse@informatik.uni-leipzig.de
 *
 */
package de.topicmapslab.ctm.writer.templates;

import static de.topicmapslab.ctm.writer.utility.CTMTokens.TOPICVARIABLE;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.tmapi.core.Construct;

import de.topicmapslab.ctm.writer.exception.SerializerException;
import de.topicmapslab.ctm.writer.templates.entry.AssociationEntry;
import de.topicmapslab.ctm.writer.templates.entry.NameEntry;
import de.topicmapslab.ctm.writer.templates.entry.OccurrenceEntry;
import de.topicmapslab.ctm.writer.templates.entry.TemplateEntry;
import de.topicmapslab.ctm.writer.templates.entry.base.IEntry;
import de.topicmapslab.ctm.writer.templates.entry.param.IEntryParam;
import de.topicmapslab.ctm.writer.templates.entry.param.VariableParam;
import de.topicmapslab.ctm.writer.utility.CTMBuffer;
import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * Class representing a CTM template defintion
 * 
 * @author Sven Krosse
 * @email krosse@informatik.uni-leipzig.de
 * 
 */
public class Template {

	/**
	 * the template name
	 */
	private final String templateName;

	/**
	 * the template entries
	 */
	private final List<IEntry> entries;

	/**
	 * a list of all parameters of the template
	 */
	private final List<String> variables;

	/**
	 * constructor
	 * 
	 * @param templateName
	 *            the template name
	 * @param entries
	 *            an array of entries
	 */
	protected Template(final String templateName, IEntry... entries) {
		this.templateName = templateName;
		this.entries = new LinkedList<IEntry>();
		this.variables = new LinkedList<String>();
		for (IEntry entry : entries) {
			add(entry);
		}
	}

	/**
	 * Method returns the internal name of the template.
	 * 
	 * @return the templateName
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * Method return the internal set of entries.
	 * 
	 * @return the entries
	 */
	public List<IEntry> getEntries() {
		return entries;
	}

	/**
	 * Adding a new entry to the internal list. After adding the entry to the
	 * list, the variable of the entry will be extracted and added to the list
	 * too.
	 * 
	 * @param entry
	 *            the new entry
	 */
	public void add(final IEntry entry) {
		// /*
		// * is multiple-entry of type association-entry
		// */
		// if (entry instanceof AssociationEntry) {
		// /*
		// * iterate over variables or values
		// */
		// for (String variable : ((AssociationEntry) entry)
		// .getValueOrVariables()) {
		// /*
		// * check if value is a variable and is not the default topic
		// * argument
		// */
		// if (variable.startsWith("$")
		// && !variable.equalsIgnoreCase(TOPICVARIABLE)) {
		// variables.add(variable);
		// }
		// }
		// }
		// /*
		// * is multiple-entry of type template-entry
		// */
		// else if (entry instanceof TemplateEntry) {
		// /*
		// * iterate over variables or values
		// */
		// for (String variable : ((TemplateEntry) entry)
		// .getValuesOrVariables()) {
		// /*
		// * check if value is a variable and is not the default topic
		// * argument
		// */
		// if (variable.startsWith("$")
		// && !variable.equalsIgnoreCase(TOPICVARIABLE)) {
		// variables.add(variable);
		// }
		// }
		// }
		// /*
		// * is name-entry
		// */
		// else if (entry instanceof NameEntry) {
		// IEntryParam value = entry.getParameter();
		// if (value != null
		// && value instanceof VariableParam
		// && !value.getCTMRepresentation().equalsIgnoreCase(
		// TOPICVARIABLE)) {
		// variables.add(value.getCTMRepresentation());
		// }
		//
		// NameEntry nameEntry = (NameEntry) entry;
		// /*
		// * check if name entry contains a variable dependent reifier entry
		// */
		// if (nameEntry.getReifierEntry() != null
		// && nameEntry.getReifierEntry().getReifierParameter()
		// .toString().startsWith("$")) {
		// variables.add(nameEntry.getReifierEntry().getReifierParameter()
		// .toString());
		// }
		// /*
		// * check if name entry contains a scope entry
		// */
		// if (nameEntry.getScopeEntry() != null) {
		// for (String variable : nameEntry.getScopeEntry().getVariables()) {
		// variables.add(variable);
		// }
		// }
		// }
		// /*
		// * is occurrence-entry
		// */
		// else if (entry instanceof OccurrenceEntry) {
		// IEntryParam value = entry.getParameter();
		// if (value != null
		// && value instanceof VariableParam
		// && !value.getCTMRepresentation().equalsIgnoreCase(
		// TOPICVARIABLE)) {
		// variables.add(value.getCTMRepresentation());
		// }
		//
		// OccurrenceEntry occurrenceEntry = (OccurrenceEntry) entry;
		// /*
		// * check if occurrence entry contains a variable dependent reifier
		// * entry
		// */
		// if (occurrenceEntry.getReifierEntry() != null
		// && occurrenceEntry.getReifierEntry().getReifierParameter()
		// .toString().startsWith("$")) {
		// variables.add(occurrenceEntry.getReifierEntry()
		// .getReifierParameter().toString());
		// }
		// /*
		// * check if occurrence entry contains a scope entry
		// */
		// if (occurrenceEntry.getScopeEntry() != null) {
		// for (String variable : occurrenceEntry.getScopeEntry()
		// .getVariables()) {
		// variables.add(variable);
		// }
		// }
		// }
		// /*
		// * is simple-entry
		// */
		// else {
		// final IEntryParam value = entry.getParameter();
		// /*
		// * check if value exists, is a variable and is not the default topic
		// * argument
		// */
		// if (value != null
		// && value instanceof VariableParam
		// && !value.getCTMRepresentation().equalsIgnoreCase(
		// TOPICVARIABLE)) {
		// variables.add(value.getCTMRepresentation());
		// }
		//
		// }
		variables.addAll(entry.getVariables());

		this.entries.add(entry);
	}

	/**
	 * Removing an entry from the internal list. After removing the entry, the
	 * variables of the entry will be extracted and removed from variable list.
	 * 
	 * @param entry
	 *            the entry to remove
	 */
	protected void remove(final IEntry entry) {
		/*
		 * is multiple-entry of type association-entry
		 */
		if (entry instanceof AssociationEntry) {
			/*
			 * iterate over variables or values
			 */
			for (String variable : ((AssociationEntry) entry)
					.getValueOrVariables()) {
				/*
				 * check if value is a variable
				 */
				if (variable.startsWith("$")) {
					variables.remove(variable);
				}
			}
		}
		/*
		 * is multiple-entry of type association-entry
		 */
		else if (entry instanceof TemplateEntry) {
			/*
			 * iterate over variables or values
			 */
			for (String variable : ((TemplateEntry) entry)
					.getValuesOrVariables()) {
				/*
				 * check if value is a variable
				 */
				if (variable.startsWith("$")) {
					variables.remove(variable);
				}
			}
		}
		/*
		 * is name-entry
		 */
		else if (entry instanceof NameEntry) {
			IEntryParam value = entry.getParameter();
			if (value != null
					&& value instanceof VariableParam
					&& !value.getCTMRepresentation().equalsIgnoreCase(
							TOPICVARIABLE)) {
				variables.remove(value.getCTMRepresentation());
			}

			NameEntry nameEntry = (NameEntry) entry;
			/*
			 * check if name entry contains a variable dependent reifier entry
			 */
			if (nameEntry.getReifierEntry() != null
					&& nameEntry.getReifierEntry().getReifierParameter()
							.toString().startsWith("$")) {
				variables.remove(nameEntry.getReifierEntry()
						.getReifierParameter().toString());
			}
			/*
			 * check if name entry contains a scope entry
			 */
			if (nameEntry.getScopeEntry() != null) {
				for (String variable : nameEntry.getScopeEntry().getVariables()) {
					variables.remove(variable);
				}
			}
		}
		/*
		 * is occurrence-entry
		 */
		else if (entry instanceof OccurrenceEntry) {
			IEntryParam value = entry.getParameter();
			if (value != null
					&& value instanceof VariableParam
					&& !value.getCTMRepresentation().equalsIgnoreCase(
							TOPICVARIABLE)) {
				variables.remove(value.getCTMRepresentation());
			}

			OccurrenceEntry occurrenceEntry = (OccurrenceEntry) entry;
			/*
			 * check if occurrence entry contains a variable dependent reifier
			 * entry
			 */
			if (occurrenceEntry.getReifierEntry() != null
					&& occurrenceEntry.getReifierEntry().getReifierParameter()
							.toString().startsWith("$")) {
				variables.remove(occurrenceEntry.getReifierEntry()
						.getReifierParameter().toString());
			}
			/*
			 * check if occurrence entry contains a scope entry
			 */
			if (occurrenceEntry.getScopeEntry() != null) {
				for (String variable : occurrenceEntry.getScopeEntry()
						.getVariables()) {
					variables.remove(variable);
				}
			}
		}
		/*
		 * is simple-entry
		 */
		else {
			IEntryParam value = entry.getParameter();
			/*
			 * check if value exists and is a variable
			 */
			if (value != null && value instanceof VariableParam) {
				variables.remove(value.getCTMRepresentation());
			}
		}
		entries.remove(entry);
	}

	/**
	 * Check if entry is adaptive for given construct. Method iterates over all
	 * contained entries and redirect to {@link IEntry#isAdaptiveFor(Construct)}
	 * .
	 * 
	 * @param construct
	 *            the construct
	 * @return <code>true</code> if the entry can replaced a part of the given
	 *         construct.
	 */
	public boolean isAdaptiveFor(Construct construct) {
		for (IEntry entry : entries) {
			if (!entry.isAdaptiveFor(construct)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Method returns all internal parameter arguments of the template
	 * definition.
	 * 
	 * @return the variables a list of all parameters
	 */
	public List<String> getVariables() {
		return variables;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Template) {
			Template template = (Template) obj;
			for (IEntry entry : getEntries()) {
				if (!template.getEntries().contains(entry)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * Method checks if internal entry set only contains entries of the given
	 * type
	 * 
	 * @param clazz
	 *            the type to check
	 * @return <code>true</code> if internal entry set only contains entries of
	 *         the given type, <code>false</code> otherwise.
	 */
	@SuppressWarnings("unchecked")
	public boolean containsOnlyInstanceOf(Class<? extends IEntry>... classes) {
		List<Class<? extends IEntry>> list = Arrays.asList(classes);
		for (IEntry entry : entries) {
			if (!list.contains(entry.getClass())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Redirects to {@link TemplateSerializer#serialize(CTMBuffer)} <br />
	 * <br /> {@inheritDoc}
	 */
	@Override
	public String toString() {
		CTMBuffer buffer = new CTMBuffer();
		try {
			new TemplateSerializer(this).serialize(buffer);
		} catch (SerializerException e) {
			e.printStackTrace();
		}
		return buffer.getBuffer().toString();
	}

	public static final Set<Template> fromCTM(final File file)
			throws SerializerException {
		throw new UnsupportedOperationException("not implemented yet.");
		// Set<Template> templates = new HashSet<Template>();
		//		
		// return templates;
	}

}
