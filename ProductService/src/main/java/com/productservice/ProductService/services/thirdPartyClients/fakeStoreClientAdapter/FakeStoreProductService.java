package com.productservice.ProductService.services.thirdPartyClients.fakeStoreClientAdapter;

// @Service("fakeStoreProductService")
// public class FakeStoreProductService  implements  ProductService{
//       private FakeStoreClientAdapter fakeStoreClientAdapter;
//       private TokenValidator tokenValidator;
//       public FakeStoreProductService(FakeStoreClientAdapter fakeStoreClientAdapter,TokenValidator tokenValidator){
//             this.fakeStoreClientAdapter = fakeStoreClientAdapter;
//             this.tokenValidator = tokenValidator; 
//       }
//       @Override
//       public GenericProductDto getProductById(String authToken,Long id) throws ProductNotFoundException {
//             // System.out.println(authToken);//successfully getting authToken
//             Optional<JwtObject> jwtObjectOptional = tokenValidator.validateToken(authToken); 
//             if (jwtObjectOptional.isEmpty()){
//                   //invalid token
//                   //reject the request
//                   return null;   
//             }
//             JwtObject jwtObject = jwtObjectOptional.get();
//             // Long userId = jwtObject.getUserId();
//             // if (specialIds.isPresent&&userId==10) {
//             //       dont allow the request
//             // }
            
//             // return convertToGenericProductDto(fakeStoreClientAdapter.getProductById(id));
//             return null;
//       }

//       @Override
//       public List<GenericProductDto> getAllProducts() {
//             List<FakeStoreProductDto>fakeStoreProductDtos = fakeStoreClientAdapter.getAllProducts();
//             List<GenericProductDto> genericProductDtos = new ArrayList<>();
//             // for (FakeStoreProductDto fakeStoreProductDto:fakeStoreProductDtos){
//             //       genericProductDtos.add(convertToGenericProductDto(fakeStoreProductDto));
//             // }
//             return genericProductDtos;
//       }

//       @Override
//       public GenericProductDto deleteProduct(Long id) throws ProductNotFoundException {
//             //  return convertToGenericProductDto(fakeStoreClientAdapter.deleteProduct(id));
//             return null;
//       }

//       @Override
//       public GenericProductDto createProduct(GenericProductDto genericProductDto) {
//             // return convertToGenericProductDto(fakeStoreClientAdapter.createProduct(genericProductDto));
//             return null;
//       }

//       @Override
//       public GenericProductDto updateProduct(Long id, GenericProductDto genericProductDto) throws ProductNotFoundException{
//             return updateProduct(id, genericProductDto);
//       }

      // private static GenericProductDto convertToGenericProductDto(FakeStoreProductDto fakeStoreProductDto){
      //       GenericProductDto genericProductDto = new GenericProductDto();
      //       genericProductDto.setId(fakeStoreProductDto.getId());
      //       genericProductDto.setImage(fakeStoreProductDto.getImage());
      //       genericProductDto.setDescription(fakeStoreProductDto.getDescription());
      //       genericProductDto.setCategory(fakeStoreProductDto.getCategory());
      //       genericProductDto.setTitle(fakeStoreProductDto.getTitle());
      //       genericProductDto.setPrice(fakeStoreProductDto.getPrice());
      //       return genericProductDto;
      // }
      
      
// }
