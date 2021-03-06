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

package com.broadcom.lsp.domain.cobol.databus.impl;

import com.broadcom.lsp.domain.cobol.databus.api.DataBusObserver;
import com.broadcom.lsp.domain.cobol.model.RequiredCopybookEvent;
import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/** A subscriber for {@link RequiredCopybookEvent} */
@Slf4j
@RequiredArgsConstructor
public class RequiredCopybookEventSubscriber {

  @Getter @NonNull private DataBusObserver observer;

  @NonNull @Getter private RequiredCopybookEvent eventType;

  @Subscribe
  @AllowConcurrentEvents
  @SneakyThrows
  public void onDataHandler(RequiredCopybookEvent eventType) {
    LOG.debug(eventType.getHeader());
    observer.observerCallback(eventType);
  }
}
