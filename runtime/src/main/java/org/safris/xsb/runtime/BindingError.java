/* Copyright (c) 2006 Seva Safris
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

package org.safris.xsb.runtime;

import org.safris.commons.lang.Throwables;
import org.safris.commons.xml.XMLError;

public final class BindingError extends XMLError {
  private static final long serialVersionUID = 3337621621621474582L;

  public BindingError() {
    super();
  }

  public BindingError(final String message) {
    super(message);
  }

  public BindingError(final Throwable cause) {
    super(cause);
    Throwables.set(this, cause.getMessage() != null ? cause.getMessage() : cause.getClass().getSimpleName(), cause);
  }

  public BindingError(final String message, final Throwable cause) {
    super(message, cause);
    Throwables.set(this, message != null ? message : (cause.getMessage() != null ? cause.getMessage() : cause.getClass().getSimpleName()), cause);
  }
}