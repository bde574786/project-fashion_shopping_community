package com.shop.fashion.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.fashion.auth.PrincipalUserDetail;
import com.shop.fashion.dto.RequestItemDto;
import com.shop.fashion.dto.ResponseDto;
import com.shop.fashion.model.Domain;
import com.shop.fashion.model.Item;
import com.shop.fashion.model.User;
import com.shop.fashion.service.BasketService;
import com.shop.fashion.service.ShoppingService;
import com.shop.fashion.service.UserService;

@RestController
public class ShoppingApiController {

	@Autowired
	BasketService basketService;

	@Autowired
	ShoppingService shoppingService;

	@Autowired
	UserService userService;

	@PostMapping("/api/item")
	public ResponseDto<String> save(@RequestBody Item item, HttpServletRequest request) {
		shoppingService.saveItem(item);
		String responceResult = "0";
		if (request.getHeader("Referer") != null) {
			responceResult = String.valueOf(request.getHeader("Referer"));
		}
		return new ResponseDto<>(HttpStatus.OK.value(), responceResult);
	}

	@PostMapping("/api/category")
	public ResponseDto<Integer> category(@RequestBody Item item) {
		shoppingService.saveItem(item);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@PostMapping("/test/api/search/{gender}/{category}")
	public ResponseDto<Page<Item>> searchMansShirts(@RequestBody RequestItemDto itemDto,
			@PageableDefault(page = 0, direction = Direction.DESC, size = 5) Pageable pageable) {
		Page<Item> page = shoppingService.searchItemCategory(itemDto.getCategory(), itemDto.getGender(), pageable);
		return new ResponseDto<Page<Item>>(HttpStatus.OK.value(), page);
	}

	@GetMapping("/test/api/getDomain")
	public Domain searchGenderCategory() {
		Domain domain = new Domain();
		return domain;
	}

	@PostMapping("/test/api/cart")
	public ResponseDto<Integer> cart(@RequestParam int itemId, @AuthenticationPrincipal PrincipalUserDetail detail) {

		System.out.println(itemId);
		// 정보
		User user = userService.getUser(detail.getUser().getId());

		Item item = shoppingService.itemDetail(itemId);

		basketService.putCart(item, user);

		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@GetMapping("/test/api/sum")
	public int sum(@AuthenticationPrincipal PrincipalUserDetail detail) {

		int user = detail.getUser().getId();

		int basket = basketService.sum(user);

		return basket;
	}

	@DeleteMapping("/test/api/basket/{basketId}")
	public ResponseDto<Integer> deleteBasketItem(@PathVariable int basketId,
			@AuthenticationPrincipal PrincipalUserDetail detail) {

		basketService.deleteBasketItemById(basketId);

		return new ResponseDto<Integer>(HttpStatus.OK.value(), detail.getUser().getId());
	}
	

}
