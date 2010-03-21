/*
 * Copyright: Copyright 2010 Topic Maps Lab, University of Leipzig. http://www.topicmapslab.de/    
 * License:   Apache License, Version 2.0 http://www.apache.org/licenses/LICENSE-2.0.html
 * 
 * @author Sven Krosse
 * @email krosse@informatik.uni-leipzig.de
 *
 */
package de.topicmapslab.ctm.writer.templates.entry;

import org.tmapi.core.Association;
import org.tmapi.core.Role;
import org.tmapi.core.Topic;

import de.topicmapslab.ctm.writer.core.CTMTopicMapWriter;
import de.topicmapslab.ctm.writer.exception.NoIdentityException;
import de.topicmapslab.ctm.writer.exception.SerializerException;

/**
 * Class representing a template-entry definition of an role-entry.
 * 
 * @author Sven Krosse
 * @email krosse@informatik.uni-leipzig.de
 * 
 */
public class RoleEntry {
	/**
	 * the parent topic map writer
	 */
	private final CTMTopicMapWriter writer;
	/**
	 * the role type
	 */
	public Topic roleType;
	/**
	 * the topic or variable playing the role
	 */
	public Object topicOrVariable;

	/**
	 * constructor
	 * 
	 * @param writer
	 *            the parent topic map writer
	 * @param roleType
	 *            the role type
	 * @param topicOrVariable
	 *            the topic or variable playing the role
	 */
	protected RoleEntry(CTMTopicMapWriter writer, Topic roleType,
			Object topicOrVariable) {
		this.roleType = roleType;
		this.topicOrVariable = topicOrVariable;
		this.writer = writer;
	}

	/**
	 * Check if entry is adaptive for given association.
	 * 
	 * @param association
	 *            the association
	 * @return <code>true</code> if the entry can replaced a part of the given
	 *         association, this means if the type and all roles an adaptive
	 *         for.
	 */
	public boolean isAdaptiveFor(Association association) {
		for (Role role : association.getRoles(roleType)) {
			if (topicOrVariable instanceof String) {
				return true;
			} else if (role.getPlayer().equals((Topic) topicOrVariable)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RoleEntry) {
			return super.equals(obj)
					&& roleType.equals(((RoleEntry) obj).roleType)
					&& topicOrVariable
							.equals(((RoleEntry) obj).topicOrVariable);
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
	 * Static method to create a role-entry by given role construct of an
	 * association. All information needed to define the entry are extracted
	 * from the given construct.
	 * 
	 * @param writer
	 *            the parent topic map writer
	 * 
	 * @param role
	 *            the role construct
	 * @return the generate role-entry
	 * 
	 * @throws SerializerException
	 *             thrown if generation failed
	 */
	public static RoleEntry buildFromConstruct(final CTMTopicMapWriter writer,
			final Role role) throws SerializerException {
		Topic type = role.getType();
		/*
		 * generate variable name
		 */
//		String variable = "$"
//				+ writer.getCtmIdentity().getMainIdentifier(
//						writer.getProperties(), type);
		String variable = "$player";
		/*
		 * create new role-entry
		 */
		return new RoleEntry(writer, type, variable);
	}

	/**
	 * Method return the internal role player as a variable name or a CTM
	 * identifier of a topic
	 * 
	 * @return the internal role player as a variable name or a CTM identifier
	 *         of a topic
	 */
	public String getTopicOrVariable() {
		if (topicOrVariable instanceof Topic) {
			try {
				// return ctmIdentity.getPrefixedIdentity((Topic)
				// topicOrVariable);
				return writer.getCtmIdentity().getMainIdentifier(
						writer.getProperties(), (Topic) topicOrVariable)
						.toString();
			} catch (NoIdentityException e) {
				e.printStackTrace();
			}
		}
		return topicOrVariable.toString();
	}

	/**
	 * Setter of the internal role player as a variable name or a CTM identifier
	 * of a topic.
	 * 
	 * @param topicOrVariable
	 *            the new internal role player as a variable name or a CTM
	 *            identifier of a topic
	 */
	public void setTopicOrVariable(Object topicOrVariable) {
		this.topicOrVariable = topicOrVariable;
	}

}
