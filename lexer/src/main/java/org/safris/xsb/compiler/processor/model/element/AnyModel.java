/* Copyright (c) 2008 Seva Safris
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * You should have received a copy of The MIT License (MIT) along with this
 * program. If not, see <http://opensource.org/licenses/MIT/>.
 */

package org.safris.xsb.compiler.processor.model.element;

import org.safris.xsb.compiler.processor.model.AnyableModel;
import org.safris.xsb.compiler.processor.model.Model;
import org.safris.xsb.compiler.schema.attribute.Namespace;
import org.safris.xsb.compiler.schema.attribute.ProcessContents;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public final class AnyModel extends ElementModel implements AnyableModel {
  private Namespace namespace = Namespace.ANY;
  private ProcessContents processContents = ProcessContents.STRICT;

  protected AnyModel(final Node node, final Model parent) {
    super(node, parent);
    final NamedNodeMap attributes = node.getAttributes();
    for (int i = 0; i < attributes.getLength(); i++) {
      final Node attribute = attributes.item(i);
      if ("namespace".equals(attribute.getLocalName()))
        namespace = Namespace.parseNamespace(attribute.getNodeValue());
      else if ("processContents".equals(attribute.getLocalName()))
        processContents = ProcessContents.parseProcessContents(attribute.getNodeValue());
    }
  }

  @Override
  public final Namespace getNamespace() {
    return namespace;
  }

  @Override
  public final ProcessContents getProcessContents() {
    return processContents;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;

    if (!(obj instanceof AnyModel))
      return false;

    AnyModel any = (AnyModel)obj;
    return namespace.equals(any.namespace) && processContents.equals(any.processContents) && getMaxOccurs().equals(any.getMaxOccurs()) && getMinOccurs().equals(any.getMinOccurs());
  }

  @Override
  public String toString() {
    return super.toString().replace(TO_STRING_DELIMITER, "namespace=\"" + namespace + "\" processContents=\"" + processContents + "\"");
  }
}