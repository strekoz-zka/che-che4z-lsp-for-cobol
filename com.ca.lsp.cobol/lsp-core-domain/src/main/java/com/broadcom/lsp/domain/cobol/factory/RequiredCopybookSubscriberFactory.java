/*
 * Copyright (c) 2019 Broadcom.
 *
 * The term "Broadcom" refers to Broadcom Inc. and/or its subsidiaries.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Broadcom, Inc. - initial API and implementation
 *
 */

package com.broadcom.lsp.domain.cobol.factory;

import com.broadcom.lsp.domain.cobol.databus.api.DataBusObserver;
import com.broadcom.lsp.domain.cobol.databus.api.SubscriberAbstractFactory;
import com.broadcom.lsp.domain.cobol.databus.impl.RequiredCopybookEventSubscriber;
import com.broadcom.lsp.domain.cobol.model.RequiredCopybookEvent;

/** This class is a factory for {@link RequiredCopybookEventSubscriber} */
public class RequiredCopybookSubscriberFactory
    implements SubscriberAbstractFactory<RequiredCopybookEventSubscriber> {
  @Override
  public RequiredCopybookEventSubscriber create(DataBusObserver observer) {
    return new RequiredCopybookEventSubscriber(observer, RequiredCopybookEvent.builder().build());
  }
}
