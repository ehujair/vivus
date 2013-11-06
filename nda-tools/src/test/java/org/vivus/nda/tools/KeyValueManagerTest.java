package org.vivus.nda.tools;

import junit.framework.Assert;

import org.junit.Test;
import org.vivus.nda.tools.cmd.LoadKeyValueCmd;
import org.vivus.nda.tools.cmd.UpdateKeyValueCmd;
import org.vivus.nda.tools.context.ICommandExecutor;
import org.vivus.nda.tools.entity.KeyValue;
import org.vivus.nda.tools.impl.Engine;

public class KeyValueManagerTest extends NdaToolsTest {
	private static final String KEY_MAC_ADDRESS = "MAC_ADDRESS";
	ICommandExecutor commandExecutor = ((Engine) engine).getConfiguration().getCommandExecutor();

	@Test
	public void testLoad() {
		KeyValue keyValue = commandExecutor.execute(new LoadKeyValueCmd(KEY_MAC_ADDRESS));
		Assert.assertNotNull(keyValue);
	}

	@Test
	public void testUpdate() {
		KeyValue keyValue = commandExecutor.execute(new LoadKeyValueCmd(KEY_MAC_ADDRESS));
		Assert.assertNotNull(keyValue);
		int newValue = keyValue.getCurrentValue() + 1;
		keyValue.setCurrentValue(newValue);
		commandExecutor.execute(new UpdateKeyValueCmd(keyValue));
		KeyValue newKeyValue = commandExecutor.execute(new LoadKeyValueCmd(KEY_MAC_ADDRESS));
		Assert.assertEquals(newValue, newKeyValue.getCurrentValue());
	}
}
