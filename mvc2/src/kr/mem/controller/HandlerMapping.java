package kr.mem.controller;

import java.util.HashMap;

import kr.mem.pojo.Controller;

// 한꺼번에 임폴트!
import kr.mem.pojo.*;

public class HandlerMapping {

	// 자료구조를 하나 가지고 있어야 한다! --> 해쉬맵 (key,value)
	private HashMap<String, Controller> mappings;

	public HandlerMapping() {

		mappings = new HashMap<String, Controller>();
		initMap();
	}

	// 중요한 포인트!! --> 여기서 보내주는 거야아아
	// 프론트컨트롤러에서 요청이 오면, 키값 중 쭉 검색을 해서 선택된 컨트롤러 넘겨줘 : 일 시킬게!
	private void initMap() {

		try {
			mappings.put("/list.do", new MemberListController());
			mappings.put("/insert.do", new MemberInsertController());
			mappings.put("/insertForm.do", new MemberInsertFormController());
			mappings.put("/delete.do", new MemberDeleteController());
			mappings.put("/content.do", new MemberContentController());
			mappings.put("/update.do",  new MemberUpdateController());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public Controller getController(String key) {
		
		return mappings.get(key);
		
	}

}
