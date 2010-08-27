package com.googlecode.goodsamples.refactoring.servicestop;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.googlecode.goodsamples.refactoring.stop.support.FailedReadOnlyNotificationException;
import com.googlecode.goodsamples.refactoring.stop.support.FailedStopException;
import com.googlecode.goodsamples.refactoring.stop.support.ReadOnlyNotifier;
import com.googlecode.goodsamples.refactoring.stop.support.Stop;
import com.googlecode.goodsamples.refactoring.stop.support.StopMessage;

@RunWith(MockitoJUnitRunner.class)
public final class ServiceStopperTest {
	private final ServiceStopper target = new ServiceStopper();
	private final Integer completeStopType = 1;
	private final Integer readOnlyStopType = 2;
	private final Boolean success = Boolean.TRUE;
	private final Boolean fail = Boolean.FALSE;

	@Mock
	private Stop completeStop;
	@Mock
	private Stop readOnlyStop;
	@Mock
	private ReadOnlyNotifier readOnlyNotifier;

	@Test
	public void serviceShouldBeStopped_WhenNeedingCompleteStop() {
		when(completeStop.stopNow()).thenReturn(Boolean.TRUE);

		target.stop(completeStopType, "Completely stop test");

		verify(completeStop).stopNow();
	}

	@Test
	public void serviceShouldBeStopped_WhenNeedingReadOnlyStop() {
		when(readOnlyStop.stopNow()).thenReturn(success);
		when(readOnlyNotifier.getRelatedServices()).thenReturn(
				createRelatedServices());
		when(
				readOnlyNotifier.notifyToRelatedServices(Mockito
						.any(StopMessage.class))).thenReturn(success);

		target.stop(readOnlyStopType, "Stop in read-only test");

		verify(readOnlyStop).stopNow();
		verify(readOnlyNotifier).notifyToRelatedServices(
				Mockito.any(StopMessage.class));
	}

	@Test
	public void serviceShouldBeStopped_WhenNeedingReadOnlyStop_WithoutReadOnlyNotification() {
		final List<String> noRelatedServices = new ArrayList<String>();
		when(readOnlyStop.stopNow()).thenReturn(success);
		when(readOnlyNotifier.getRelatedServices()).thenReturn(noRelatedServices);

		target.stop(readOnlyStopType, "Stop in read-only test");
		
		verify(readOnlyNotifier).getRelatedServices();
		verifyNoMoreInteractions(readOnlyNotifier);
	}

	@Test(expected = FailedReadOnlyNotificationException.class)
	public void whenFailingReadOnlyNotification() {
		when(readOnlyStop.stopNow()).thenReturn(success);
		when(readOnlyNotifier.getRelatedServices()).thenReturn(
				createRelatedServices());
		when(
				readOnlyNotifier.notifyToRelatedServices(Mockito
						.any(StopMessage.class))).thenReturn(fail);

		target.stop(readOnlyStopType, "Some reason");
	}

	@Test(expected = FailedStopException.class)
	public void whenFailingCompleteStop() {
		when(completeStop.stopNow()).thenReturn(fail);

		target.stop(completeStopType, "Complete stop test");
	}

	@Test(expected = FailedStopException.class)
	public void whenFailingReadOnlyStop() {
		when(readOnlyStop.stopNow()).thenReturn(fail);

		target.stop(readOnlyStopType, "Stop in read-only mode test");
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenStopTypeIsNull() {
		final Integer stopType = null;
		target.stop(stopType, "Some reason");
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenStopTypeIsInvalid() {
		final Integer notSupportedType = -1;
		target.stop(notSupportedType, "Some reason");
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenReasonIsNull() {
		final Integer someType = 1;
		final String reason = null;
		target.stop(someType, reason);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenReasonIsEmpty() {
		final Integer someType = 1;
		final String reason = "";
		target.stop(someType, reason);
	}

	private List<String> createRelatedServices() {
		List<String> relatedServices = new ArrayList<String>();
		relatedServices.add("someService");
		return relatedServices;
	}

	@Before
	public void prepareColloboratorsToTest() {
		target.completeStop = completeStop;
		target.readOnlyStop = readOnlyStop;
		target.readOnlyNotifier = readOnlyNotifier;
	}
}
