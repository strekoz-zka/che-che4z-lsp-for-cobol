/*
 * Copyright (c) 2019 Broadcom.
 * The term "Broadcom" refers to Broadcom Inc. and/or its subsidiaries.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Broadcom, Inc. - initial API and implementation
 */
package com.ca.lsp.core.cobol.parser.error;

import java.util.List;

import com.ca.lsp.core.cobol.parser.error.objects.ErrorPosition;

import lombok.Builder;
import lombok.Value;

@Value
public class SyntaxError {
  private final ErrorPosition position;
  private final List<String> ruleStack;
  private final int type;
  private final String suggestion;
  private final int severity;

  public String printSyntaxError() {
    return super.toString().concat(this.toString());
  }

  @Builder(builderMethodName = "syntaxerror")
  public SyntaxError(
      ErrorPosition position, List<String> ruleStack, int type, String suggestion, int severity) {
    this.position = position;
    this.ruleStack = ruleStack;
    this.type = type;
    this.suggestion = suggestion;
    this.severity = severity;
  }
}