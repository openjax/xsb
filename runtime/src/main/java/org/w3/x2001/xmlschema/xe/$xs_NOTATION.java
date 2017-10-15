/* Copyright (c) 2006 lib4j
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

package org.w3.x2001.xmlschema.xe;

import org.libx4j.xsb.runtime.MarshalException;
import org.libx4j.xsb.runtime.NotationType;
import org.libx4j.xsb.runtime.ParseException;
import org.w3c.dom.Element;

public abstract class $xs_NOTATION extends $xs_anySimpleType {
  private static final long serialVersionUID = 5701767081230621619L;

  public $xs_NOTATION(final $xs_NOTATION binding) {
    super(binding);
  }

  public $xs_NOTATION(final NotationType value) {
    super(value);
  }

  protected $xs_NOTATION() {
    super();
  }

  @Override
  public NotationType text() {
    return (NotationType)super.text();
  }

  public void text(final NotationType text) {
    super.text(text);
  }

  @Override
  protected void _$$decode(final Element parent, final String value) throws ParseException {
    super.text(NotationType.parse(value));
    if (super.text() == null)
      throw new ParseException("Notation \"" + value + "\" is not registered. The code that instantiates the Notation binding for \"" + value + "\" must be run before it is possible for the Binding engine to have to know about it.");
  }

  @Override
  protected String _$$encode(final Element parent) throws MarshalException {
    return super.text() != null ? super.text().toString() : "";
  }

  @Override
  public $xs_NOTATION clone() {
    return new $xs_NOTATION(this) {
      private static final long serialVersionUID = 5354182416535222789L;

      @Override
      protected $xs_NOTATION inherits() {
        return this;
      }
    };
  }
}