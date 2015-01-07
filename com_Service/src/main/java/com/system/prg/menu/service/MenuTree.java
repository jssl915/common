package com.system.prg.menu.service;

import java.util.ArrayList;
import java.util.List;

import com.system.prg.menu.entity.SMenu;
import com.system.prg.util.Tree;
import com.system.prg.util.VerifyObject;

public class MenuTree extends Tree<MenuTree> {
	public MenuTree() {}
	public MenuTree(SMenu r) {
		this.setText(r.getMenuName());
		this.setId(r.getMenuId().toString());
		this.setExpanded(expanded);
		this.setPid(r.getMenuPid().toString());
	}

	public void setChildren(List<SMenu> MenuList, boolean expanded) {
		if (VerifyObject.verifyCollection(MenuList)) {
			List<MenuTree> list = new ArrayList<MenuTree>();
			MenuTree rt;
			for (SMenu r : MenuList) {
				rt = new MenuTree();
				rt.setText(r.getMenuName());
				rt.setId(r.getMenuId().toString());
				rt.setExpanded(expanded);
				rt.setPid(r.getMenuPid().toString());
				list.add(rt);
			}
			this.setChildren(list);
		}
	}

	public void addChildren(MenuTree r, boolean expanded) {
		if (r != null) {
			r.setExpanded(expanded);
			if (this.getChildren() == null) {
				this.setChildren(new ArrayList<MenuTree>());
			}
			this.getChildren().add(r);
		}
	}

}
