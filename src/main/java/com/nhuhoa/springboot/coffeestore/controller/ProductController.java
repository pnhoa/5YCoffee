package com.nhuhoa.springboot.coffeestore.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nhuhoa.springboot.coffeestore.dto.ProductDTO;
import com.nhuhoa.springboot.coffeestore.paging.IPaging;
import com.nhuhoa.springboot.coffeestore.paging.Paging;
import com.nhuhoa.springboot.coffeestore.service.web.CategoryService;
import com.nhuhoa.springboot.coffeestore.service.web.ProductService;
import com.nhuhoa.springboot.coffeestore.utils.MessageUtil;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
	
	public Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private MessageUtil messageUtil;
	
		
	
	@GetMapping("/list")
	public String showListProduct(Model theModel, @RequestParam(value="page", defaultValue = "1") int page,
			@RequestParam(value="limit", defaultValue = "5") int limit,
			@RequestParam(value="message", required = false) String message) {
		
		ProductDTO model = new ProductDTO();
		
		IPaging paging = new Paging(page, limit);
		
		Iterable<ProductDTO> theProductDto = productService.findAll(paging);
		
		theModel.addAttribute("products", theProductDto);
		
		model.setPage(page);
		model.setLimit(limit);
		model.setTotalItem(productService.count());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / paging.getLimit()));
		
		if(message != null) {
			Map<String, String> mess =  messageUtil.getMessage(message);
			model.setAlert(mess.get("alert"));
			model.setMessage(mess.get("message"));
		}
		
		theModel.addAttribute("model", model);
		
		
		return "admin/product/list-product";
	}
	
	@GetMapping("/{id}")
	public String showProductDetail(Model theModel, @PathVariable("id") Long theId) {
		
		ProductDTO theProductDTO = productService.findById(theId);
		
		theModel.addAttribute("product", theProductDTO);
		
		
		return "admin/product/product-detail";
	}
	
	
	@GetMapping("/edit")
	public String showFormForAdd(Model theModel, @RequestParam(value="id", required = false) Long id) {
		
		ProductDTO productDto = new ProductDTO();
		
		if(id != null) {
			productDto = productService.findById(id);
		}
		
		theModel.addAttribute("product", productDto);
		
		theModel.addAttribute("categories", categoryService.findAllReturnMap(new IPaging() {
			
			@Override
			public Integer getPage() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Integer getOffset() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Integer getLimit() {
				// TODO Auto-generated method stub
				return null;
			}
		}));
		
		return "admin/product/product-form";
	}
	
	@PostMapping("/save")
	public String saveProduct(@Valid @ModelAttribute("product") ProductDTO theProductDto, BindingResult theBindingResult,
			RedirectAttributes ra , @RequestParam(value="fileData", required = false) MultipartFile thumbnailFile ) throws IOException {
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 return "redirect:edit";
	        }
		
		String fileName = StringUtils.cleanPath(thumbnailFile.getOriginalFilename());
		
		theProductDto.setThumbnail(fileName);
		
		ProductDTO theProduct =  productService.save(theProductDto);
		
		// save file to folder
		if(fileName !=null && fileName.length() > 0) {
			String uploadDir = "./product/thumbnail/" + theProduct.getId();
			
			Path uploadPath = Paths.get(uploadDir);
			
			logger.info(">>>>>>>>>>>>" + uploadPath);
			
			if(!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			try(InputStream inputStream = thumbnailFile.getInputStream()){
				
				Path filePath = uploadPath.resolve(fileName);
				logger.info(">>>>>>>>>>>>" + filePath.toFile().getAbsolutePath());
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
				
			} catch (IOException e) {
				throw new IOException("Could not save uploaded file: " + fileName);
			}
			
			
			ra.addFlashAttribute("message", "The product has been saved!");
		}
		
		
		
		return "redirect:list";
	}
	
	@DeleteMapping("/")
	@ResponseBody  // response to page success or error
	public void deleteProducts(@RequestBody Long[] ids) {
		try {
			for(Long id:ids) {
				productService.remove(id);
			}
		} catch(Exception e) {
			e.printStackTrace();
		
		}
		
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("theSearchValue") String theSearchValue, Model theModel) {
		ProductDTO model = new ProductDTO();
		
		IPaging paging = new Paging(null, null);
		
		Iterable<ProductDTO> theProductDto = productService.search(paging, theSearchValue);
		
		theModel.addAttribute("products", theProductDto);
		
		model.setPage(1);
		model.setLimit(100);
		model.setTotalItem(StreamSupport.stream(theProductDto.spliterator(), false).count());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
		
		
		theModel.addAttribute("model", model);
		
		return "admin/product/list-product";
	}


}
