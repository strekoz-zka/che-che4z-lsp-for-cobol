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

package com.broadcom.impl;

import com.broadcom.lsp.cdi.LangServerCtx;
import com.broadcom.lsp.domain.cobol.databus.impl.DefaultDataBusBroker;
import com.broadcom.lsp.domain.cobol.model.DataEvent;
import com.broadcom.lsp.domain.cobol.model.DataEventType;
import com.broadcom.lsp.domain.cobol.model.RequiredCopybookEvent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeoutException;

import static org.junit.Assert.fail;

/** This test verifies that the observer is not triggered by the event it is not subscribed to. */
@Slf4j
public class DataBusSubscribeBadTest extends AbsDataBusImplTest {

  private DefaultDataBusBroker databus;

  @Before
  public void setUp() {
    databus =
        LangServerCtx.getGuiceCtx(new DatabusTestModule())
            .getInjector()
            .getInstance(DefaultDataBusBroker.class);
  }

  @After
  public void tearDown() {
    databus = null;
    LangServerCtx.shutdown();
  }

  @Override
  public void observerCallback(DataEvent adaptedDataEvent) {
    waiter.assertFalse(DataEventType.FETCHED_COPYBOOK_EVENT == adaptedDataEvent.getEventType());
    LOG.debug(String.format("Received : %s", adaptedDataEvent.getEventType().getId()));
    LOG.debug(String.format("Expected : %s", DataEventType.FETCHED_COPYBOOK_EVENT.getId()));
    waiter.resume();
  }

  @Test
  public void subscribe() {
    databus.subscribe(DataEventType.REQUIRED_COPYBOOK_EVENT, this);
    databus.postData(
        RequiredCopybookEvent.builder().name("REQUIRED_COPYBOOK_EVENT_SUBSCRIPTION TEST").build());
    try {
      waiter.await(5000);
    } catch (TimeoutException | InterruptedException e) {
      fail("No events were received");
    }
  }
}
