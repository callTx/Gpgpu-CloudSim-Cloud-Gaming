package org.cloudbus.cloudsim.lists;

import org.cloudbus.cloudsim.VmGpgpu;
import java.util.List;

public class VmGpgpuList {
	
	public static <T extends VmGpgpu> T getById(List<T> vmGpgpuList,int id) {
		for(T vm: vmGpgpuList) {
			if(vm.getId() == id) {
				return vm;
			}
		}
		return null;
	}
	
	public static <T extends VmGpgpu> T getByIdAndUserId(List<T> vmGpgpuList, int id,int userId) {
		for(T vm: vmGpgpuList) {
			if(vm.getId() == id && vm.getUserId() == userId) {
				return vm;
			}
		}
		return null;
	}
}
