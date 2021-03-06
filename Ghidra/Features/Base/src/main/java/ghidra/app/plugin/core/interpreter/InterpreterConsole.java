/* ###
 * IP: GHIDRA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ghidra.app.plugin.core.interpreter;

import java.io.*;

import docking.action.DockingAction;
import ghidra.generic.function.Callback;
import ghidra.util.Disposable;

/**
 * Interactive interpreter console.
 */
public interface InterpreterConsole extends Disposable {

	/* TODO: 
	 * The PythonPlugin is overly complicated because we have to startup a new thread that
	 * continuously checks the InterpreterConsole for new input.  It would be much easier
	 * if the InterpreterConsole could just asynchronously send an update to whoever is using 
	 * it every time a new thing is entered into it.  
	 */

	public void clear();

	public InputStream getStdin();

	public OutputStream getStdOut();

	public OutputStream getStdErr();

	public PrintWriter getOutWriter();

	public PrintWriter getErrWriter();

	public void setPrompt(String prompt);

	/**
	 * Signals that this console is one that the user can remove from the tool as desired.  If
	 * this method is not called, then the user cannot remove the console from the tool, which 
	 * means that closing the console only hides it.
	 */
	public void setTransient();
	
	public void addAction(DockingAction action);

	/**
	 * Adds the given callback which will get called the first time the interpreter console
	 * is activated.
	 * 
	 * @param activationCallback The callback to execute when activation occurs for the first time.
	 */
	public void addFirstActivationCallback(Callback activationCallback);
}
