/**
 * 
 */
package gdc.utility.dataservice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import gdc.utility.common.Key;

/**
 * @author suhada
 *
 */
public class DataTransfer implements Serializable{
	
	private String status = "N/A";
	private HashMap<String, Object> inputs = new HashMap<String,Object>();
	private HashMap<String, Object> outputs = new HashMap<String,Object>();
	
	{
		outputs.put(Key.MESSAGE_LIST, new ArrayList<String>());
		outputs.put(Key.WARNING_LIST, new ArrayList<String>());
		outputs.put(Key.ERROR_LIST, new ArrayList<String>());
	}
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the inputs
	 */
	public HashMap<String, Object> getInputs() {
		return inputs;
	}

	/**
	 * @param inputs the inputs to set
	 */
	public void setInputs(HashMap<String, Object> inputs) {
		this.inputs = inputs;
	}

	/**
	 * @return the outputs
	 */
	public HashMap<String, Object> getOutputs() {
		return outputs;
	}

	/**
	 * @param outputs the outputs to set
	 */
	public void setOutputs(HashMap<String, Object> outputs) {
		this.outputs = outputs;
	}

	public void addInput(String key,Object value) {
		this.inputs.put(key, value);
	}
	public Object getInput(String key) {
		return this.inputs.get(key);
	}
	public void addOutput(String key,Object value) {
		if(key.equalsIgnoreCase(Key.MESSAGE_LIST) 
				|| key.equalsIgnoreCase(Key.ERROR_LIST)
				|| key.equalsIgnoreCase(Key.WARNING_LIST)){
			
			((ArrayList)this.outputs.get(key)).add(value);
			
		}else if(key.equalsIgnoreCase(Key.ERROR)
				||key.equalsIgnoreCase(Key.MESSAGE)
				||key.equalsIgnoreCase(Key.WARNING)){
			String _key ="";
			
			if(key.equalsIgnoreCase(Key.ERROR))
				_key = Key.ERROR_LIST;
			else if(key.equalsIgnoreCase(Key.WARNING))
				_key = Key.WARNING_LIST;
			else if(key.equalsIgnoreCase(Key.MESSAGE) || key.equalsIgnoreCase(Key.FAIL) || key.equalsIgnoreCase(Key.INFO))
				_key = Key.MESSAGE_LIST;
			
			((ArrayList)this.outputs.get(_key)).add(value);
			this.outputs.put(key, value);
		}else {
			this.outputs.put(key, value);
		}
	}
	public Object getOutput(String key) {
		return this.outputs.get(key);
	}
	
	public int getErrorListSize() {
		return ((ArrayList)this.outputs.get(Key.ERROR_LIST)).size();
	}
	
	public int getWarningListSize() {
		return ((ArrayList)this.outputs.get(Key.WARNING_LIST)).size();
	}
	
	public void printMe() {
		System.out.println("STATUS :"+this.status);
		System.out.println("INPUTS "+this.inputs);
		System.out.println("OUTPUTS "+this.outputs);
	}

	/**
	 * @param string
	 * @return 
	 */
	public Object removeInput(String key) {
		return this.inputs.remove(key);
	}
	
	public Object removeOutput(String key) {
		return this.outputs.remove(key);
	}
	
	/**
	 * 
	 * @return if all clear should return 0.
	 */
	public int clearWarningList() {
		((ArrayList)this.outputs.get(Key.WARNING_LIST)).clear();
		return ((ArrayList)this.outputs.get(Key.WARNING_LIST)).size();
	}
	
	/**
	 * 
	 * @return if all clear should return 0.
	 */
	public int clearErrorList() {
		((ArrayList)this.outputs.get(Key.ERROR_LIST)).clear();
		return ((ArrayList)this.outputs.get(Key.ERROR_LIST)).size();
	}
	
	/**
	 * 
	 * @return if all clear should return 0.
	 */
	public int clearMessageList() {
		((ArrayList)this.outputs.get(Key.MESSAGE_LIST)).clear();
		return ((ArrayList)this.outputs.get(Key.MESSAGE_LIST)).size();
	}
	
	public void clearMessageErrorWarningLists() {
		this.clearErrorList();
		this.clearMessageList();
		this.clearWarningList();
	}
}
