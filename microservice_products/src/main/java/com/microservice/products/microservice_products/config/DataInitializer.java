package com.microservice.products.microservice_products.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microservice.products.microservice_products.models.Product;
import com.microservice.products.microservice_products.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDataBase(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                System.out.println("Iniciando carga de datos de productos...");

                List<Product> products = List.of(
                    Product.builder()
                        .title("Blue & Black Check Shirt")
                        .description("The Blue & Black Check Shirt is a stylish and comfortable men's shirt featuring a classic check pattern. Made from high-quality fabric, it's suitable for both casual and semi-formal occasions.")
                        .category("mens-shirts")
                        .price(BigDecimal.valueOf(29.99))
                        .discountPercentage(15.35)
                        .rating(3.64)
                        .stock(38)
                        .thumbnail("https://cdn.dummyjson.com/product-images/mens-shirts/blue-&-black-check-shirt/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/mens-shirts/blue-&-black-check-shirt/1.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shirts/blue-&-black-check-shirt/2.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shirts/blue-&-black-check-shirt/3.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shirts/blue-&-black-check-shirt/4.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Gigabyte Aorus Men Tshirt")
                        .description("The Gigabyte Aorus Men Tshirt is a cool and casual shirt for gaming enthusiasts. With the Aorus logo and sleek design, it's perfect for expressing your gaming style.")
                        .category("mens-shirts")
                        .price(BigDecimal.valueOf(24.99))
                        .discountPercentage(0.94)
                        .rating(3.18)
                        .stock(90)
                        .thumbnail("https://cdn.dummyjson.com/product-images/mens-shirts/gigabyte-aorus-men-tshirt/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/mens-shirts/gigabyte-aorus-men-tshirt/1.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shirts/gigabyte-aorus-men-tshirt/2.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shirts/gigabyte-aorus-men-tshirt/3.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shirts/gigabyte-aorus-men-tshirt/4.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Man Plaid Shirt")
                        .description("The Man Plaid Shirt is a timeless and versatile men's shirt with a classic plaid pattern. Its comfortable fit and casual style make it a wardrobe essential for various occasions.")
                        .category("mens-shirts")
                        .price(BigDecimal.valueOf(34.99))
                        .discountPercentage(19.5)
                        .rating(3.46)
                        .stock(82)
                        .thumbnail("https://cdn.dummyjson.com/product-images/mens-shirts/man-plaid-shirt/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/mens-shirts/man-plaid-shirt/1.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shirts/man-plaid-shirt/2.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shirts/man-plaid-shirt/3.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shirts/man-plaid-shirt/4.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Man Short Sleeve Shirt")
                        .description("The Man Short Sleeve Shirt is a breezy and stylish option for warm days. With a comfortable fit and short sleeves, it's perfect for a laid-back yet polished look.")
                        .category("mens-shirts")
                        .price(BigDecimal.valueOf(19.99))
                        .discountPercentage(6.83)
                        .rating(2.9)
                        .stock(2)
                        .thumbnail("https://cdn.dummyjson.com/product-images/mens-shirts/man-short-sleeve-shirt/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/mens-shirts/man-short-sleeve-shirt/1.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shirts/man-short-sleeve-shirt/2.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shirts/man-short-sleeve-shirt/3.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shirts/man-short-sleeve-shirt/4.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Men Check Shirt")
                        .description("The Men Check Shirt is a classic and versatile shirt featuring a stylish check pattern. Suitable for various occasions, it adds a smart and polished touch to your wardrobe.")
                        .category("mens-shirts")
                        .price(BigDecimal.valueOf(27.99))
                        .discountPercentage(11.38)
                        .rating(2.72)
                        .stock(95)
                        .thumbnail("https://cdn.dummyjson.com/product-images/mens-shirts/men-check-shirt/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/mens-shirts/men-check-shirt/1.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shirts/men-check-shirt/2.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shirts/men-check-shirt/3.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shirts/men-check-shirt/4.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Nike Air Jordan 1 Red And Black")
                        .description("The Nike Air Jordan 1 in Red and Black is an iconic basketball sneaker known for its stylish design and high-performance features, making it a favorite among sneaker enthusiasts and athletes.")
                        .category("mens-shoes")
                        .price(BigDecimal.valueOf(149.99))
                        .discountPercentage(4.12)
                        .rating(4.77)
                        .stock(7)
                        .thumbnail("https://cdn.dummyjson.com/product-images/mens-shoes/nike-air-jordan-1-red-and-black/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/mens-shoes/nike-air-jordan-1-red-and-black/1.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shoes/nike-air-jordan-1-red-and-black/2.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shoes/nike-air-jordan-1-red-and-black/3.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shoes/nike-air-jordan-1-red-and-black/4.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Nike Baseball Cleats")
                        .description("Nike Baseball Cleats are designed for maximum traction and performance on the baseball field. They provide stability and support for players during games and practices.")
                        .category("mens-shoes")
                        .price(BigDecimal.valueOf(79.99))
                        .discountPercentage(18.04)
                        .rating(3.88)
                        .stock(12)
                        .thumbnail("https://cdn.dummyjson.com/product-images/mens-shoes/nike-baseball-cleats/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/mens-shoes/nike-baseball-cleats/1.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shoes/nike-baseball-cleats/2.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shoes/nike-baseball-cleats/3.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shoes/nike-baseball-cleats/4.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Puma Future Rider Trainers")
                        .description("The Puma Future Rider Trainers offer a blend of retro style and modern comfort. Perfect for casual wear, these trainers provide a fashionable and comfortable option for everyday use.")
                        .category("mens-shoes")
                        .price(BigDecimal.valueOf(89.99))
                        .discountPercentage(4.2)
                        .rating(4.9)
                        .stock(90)
                        .thumbnail("https://cdn.dummyjson.com/product-images/mens-shoes/puma-future-rider-trainers/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/mens-shoes/puma-future-rider-trainers/1.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shoes/puma-future-rider-trainers/2.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shoes/puma-future-rider-trainers/3.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shoes/puma-future-rider-trainers/4.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Sports Sneakers Off White & Red")
                        .description("The Sports Sneakers in Off White and Red combine style and functionality, making them a fashionable choice for sports enthusiasts. The red and off-white color combination adds a bold and energetic touch.")
                        .category("mens-shoes")
                        .price(BigDecimal.valueOf(119.99))
                        .discountPercentage(4.97)
                        .rating(4.77)
                        .stock(17)
                        .thumbnail("https://cdn.dummyjson.com/product-images/mens-shoes/sports-sneakers-off-white-&-red/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/mens-shoes/sports-sneakers-off-white-&-red/1.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shoes/sports-sneakers-off-white-&-red/2.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shoes/sports-sneakers-off-white-&-red/3.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shoes/sports-sneakers-off-white-&-red/4.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Sports Sneakers Off White Red")
                        .description("Another variant of the Sports Sneakers in Off White Red, featuring a unique design. These sneakers offer style and comfort for casual occasions.")
                        .category("mens-shoes")
                        .price(BigDecimal.valueOf(109.99))
                        .discountPercentage(0.04)
                        .rating(4.69)
                        .stock(62)
                        .thumbnail("https://cdn.dummyjson.com/product-images/mens-shoes/sports-sneakers-off-white-red/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/mens-shoes/sports-sneakers-off-white-red/1.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shoes/sports-sneakers-off-white-red/2.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shoes/sports-sneakers-off-white-red/3.webp",
                            "https://cdn.dummyjson.com/product-images/mens-shoes/sports-sneakers-off-white-red/4.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Brown Leather Belt Watch")
                        .description("The Brown Leather Belt Watch is a stylish timepiece with a classic design. Featuring a genuine leather strap and a sleek dial, it adds a touch of sophistication to your look.")
                        .category("mens-watches")
                        .price(BigDecimal.valueOf(89.99))
                        .discountPercentage(5.99)
                        .rating(4.19)
                        .stock(32)
                        .thumbnail("https://cdn.dummyjson.com/product-images/mens-watches/brown-leather-belt-watch/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/mens-watches/brown-leather-belt-watch/1.webp",
                            "https://cdn.dummyjson.com/product-images/mens-watches/brown-leather-belt-watch/2.webp",
                            "https://cdn.dummyjson.com/product-images/mens-watches/brown-leather-belt-watch/3.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Longines Master Collection")
                        .description("The Longines Master Collection is an elegant and refined watch known for its precision and craftsmanship. With a timeless design, it's a symbol of luxury and sophistication.")
                        .category("mens-watches")
                        .price(BigDecimal.valueOf(1499.99))
                        .discountPercentage(17.24)
                        .rating(3.87)
                        .stock(100)
                        .thumbnail("https://cdn.dummyjson.com/product-images/mens-watches/longines-master-collection/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/mens-watches/longines-master-collection/1.webp",
                            "https://cdn.dummyjson.com/product-images/mens-watches/longines-master-collection/2.webp",
                            "https://cdn.dummyjson.com/product-images/mens-watches/longines-master-collection/3.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Rolex Cellini Date Black Dial")
                        .description("The Rolex Cellini Date with Black Dial is a classic and prestigious watch. With a black dial and date complication, it exudes sophistication and is a symbol of Rolex's heritage.")
                        .category("mens-watches")
                        .price(BigDecimal.valueOf(8999.99))
                        .discountPercentage(8.88)
                        .rating(4.97)
                        .stock(40)
                        .thumbnail("https://cdn.dummyjson.com/product-images/mens-watches/rolex-cellini-date-black-dial/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/mens-watches/rolex-cellini-date-black-dial/1.webp",
                            "https://cdn.dummyjson.com/product-images/mens-watches/rolex-cellini-date-black-dial/2.webp",
                            "https://cdn.dummyjson.com/product-images/mens-watches/rolex-cellini-date-black-dial/3.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Rolex Cellini Moonphase")
                        .description("The Rolex Cellini Moonphase is a masterpiece of horology, featuring a moon phase complication and exquisite design. It reflects Rolex's commitment to precision and elegance.")
                        .category("mens-watches")
                        .price(BigDecimal.valueOf(12999.99))
                        .discountPercentage(17.52)
                        .rating(2.58)
                        .stock(36)
                        .thumbnail("https://cdn.dummyjson.com/product-images/mens-watches/rolex-cellini-moonphase/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/mens-watches/rolex-cellini-moonphase/1.webp",
                            "https://cdn.dummyjson.com/product-images/mens-watches/rolex-cellini-moonphase/2.webp",
                            "https://cdn.dummyjson.com/product-images/mens-watches/rolex-cellini-moonphase/3.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Rolex Datejust")
                        .description("The Rolex Datejust is an iconic and versatile timepiece with a date window. Known for its timeless design and reliability, it's a symbol of Rolex's watchmaking excellence.")
                        .category("mens-watches")
                        .price(BigDecimal.valueOf(10999.99))
                        .discountPercentage(3.73)
                        .rating(3.66)
                        .stock(86)
                        .thumbnail("https://cdn.dummyjson.com/product-images/mens-watches/rolex-datejust/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/mens-watches/rolex-datejust/1.webp",
                            "https://cdn.dummyjson.com/product-images/mens-watches/rolex-datejust/2.webp",
                            "https://cdn.dummyjson.com/product-images/mens-watches/rolex-datejust/3.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Rolex Submariner Watch")
                        .description("The Rolex Submariner is a legendary dive watch with a rich history. Known for its durability and water resistance, it's a symbol of adventure and exploration.")
                        .category("mens-watches")
                        .price(BigDecimal.valueOf(13999.99))
                        .discountPercentage(5.05)
                        .rating(2.69)
                        .stock(55)
                        .thumbnail("https://cdn.dummyjson.com/product-images/mens-watches/rolex-submariner-watch/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/mens-watches/rolex-submariner-watch/1.webp",
                            "https://cdn.dummyjson.com/product-images/mens-watches/rolex-submariner-watch/2.webp",
                            "https://cdn.dummyjson.com/product-images/mens-watches/rolex-submariner-watch/3.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Black Sun Glasses")
                        .description("The Black Sun Glasses are a classic and stylish choice, featuring a sleek black frame and tinted lenses. They provide both UV protection and a fashionable look.")
                        .category("sunglasses")
                        .price(BigDecimal.valueOf(29.99))
                        .discountPercentage(4.94)
                        .rating(4.41)
                        .stock(60)
                        .thumbnail("https://cdn.dummyjson.com/product-images/sunglasses/black-sun-glasses/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/sunglasses/black-sun-glasses/1.webp",
                            "https://cdn.dummyjson.com/product-images/sunglasses/black-sun-glasses/2.webp",
                            "https://cdn.dummyjson.com/product-images/sunglasses/black-sun-glasses/3.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Classic Sun Glasses")
                        .description("The Classic Sun Glasses offer a timeless design with a neutral frame and UV-protected lenses. These sunglasses are versatile and suitable for various occasions.")
                        .category("sunglasses")
                        .price(BigDecimal.valueOf(24.99))
                        .discountPercentage(4.94)
                        .rating(3.86)
                        .stock(1)
                        .thumbnail("https://cdn.dummyjson.com/product-images/sunglasses/classic-sun-glasses/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/sunglasses/classic-sun-glasses/1.webp",
                            "https://cdn.dummyjson.com/product-images/sunglasses/classic-sun-glasses/2.webp",
                            "https://cdn.dummyjson.com/product-images/sunglasses/classic-sun-glasses/3.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Green and Black Glasses")
                        .description("The Green and Black Glasses feature a bold combination of green and black colors, adding a touch of vibrancy to your eyewear collection. They are both stylish and eye-catching.")
                        .category("sunglasses")
                        .price(BigDecimal.valueOf(34.99))
                        .discountPercentage(1.01)
                        .rating(4.55)
                        .stock(24)
                        .thumbnail("https://cdn.dummyjson.com/product-images/sunglasses/green-and-black-glasses/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/sunglasses/green-and-black-glasses/1.webp",
                            "https://cdn.dummyjson.com/product-images/sunglasses/green-and-black-glasses/2.webp",
                            "https://cdn.dummyjson.com/product-images/sunglasses/green-and-black-glasses/3.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Party Glasses")
                        .description("The Party Glasses are designed to add flair to your party outfit. With unique shapes or colorful frames, they're perfect for adding a playful touch to your look during celebrations.")
                        .category("sunglasses")
                        .price(BigDecimal.valueOf(19.99))
                        .discountPercentage(11.22)
                        .rating(2.79)
                        .stock(86)
                        .thumbnail("https://cdn.dummyjson.com/product-images/sunglasses/party-glasses/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/sunglasses/party-glasses/1.webp",
                            "https://cdn.dummyjson.com/product-images/sunglasses/party-glasses/2.webp",
                            "https://cdn.dummyjson.com/product-images/sunglasses/party-glasses/3.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Sunglasses")
                        .description("The Sunglasses offer a classic and simple design with a focus on functionality. These sunglasses provide essential UV protection while maintaining a timeless look.")
                        .category("sunglasses")
                        .price(BigDecimal.valueOf(22.99))
                        .discountPercentage(1.51)
                        .rating(3.02)
                        .stock(27)
                        .thumbnail("https://cdn.dummyjson.com/product-images/sunglasses/sunglasses/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/sunglasses/sunglasses/1.webp",
                            "https://cdn.dummyjson.com/product-images/sunglasses/sunglasses/2.webp",
                            "https://cdn.dummyjson.com/product-images/sunglasses/sunglasses/3.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Blue Women's Handbag")
                        .description("The Blue Women's Handbag is a stylish and spacious accessory for everyday use. With a vibrant blue color and multiple compartments, it combines fashion and functionality.")
                        .category("womens-bags")
                        .price(BigDecimal.valueOf(49.99))
                        .discountPercentage(17.88)
                        .rating(2.92)
                        .stock(76)
                        .thumbnail("https://cdn.dummyjson.com/product-images/womens-bags/blue-women's-handbag/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/womens-bags/blue-women's-handbag/1.webp",
                            "https://cdn.dummyjson.com/product-images/womens-bags/blue-women's-handbag/2.webp",
                            "https://cdn.dummyjson.com/product-images/womens-bags/blue-women's-handbag/3.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Heshe Women's Leather Bag")
                        .description("The Heshe Women's Leather Bag is a luxurious and high-quality leather bag for the sophisticated woman. With a timeless design and durable craftsmanship, it's a versatile accessory.")
                        .category("womens-bags")
                        .price(BigDecimal.valueOf(129.99))
                        .discountPercentage(3.87)
                        .rating(4.92)
                        .stock(99)
                        .thumbnail("https://cdn.dummyjson.com/product-images/womens-bags/heshe-women's-leather-bag/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/womens-bags/heshe-women's-leather-bag/1.webp",
                            "https://cdn.dummyjson.com/product-images/womens-bags/heshe-women's-leather-bag/2.webp",
                            "https://cdn.dummyjson.com/product-images/womens-bags/heshe-women's-leather-bag/3.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Prada Women Bag")
                        .description("The Prada Women Bag is an iconic designer bag that exudes elegance and luxury. Crafted with precision and featuring the Prada logo, it's a statement piece for fashion enthusiasts.")
                        .category("womens-bags")
                        .price(BigDecimal.valueOf(599.99))
                        .discountPercentage(14.09)
                        .rating(2.71)
                        .stock(75)
                        .thumbnail("https://cdn.dummyjson.com/product-images/womens-bags/prada-women-bag/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/womens-bags/prada-women-bag/1.webp",
                            "https://cdn.dummyjson.com/product-images/womens-bags/prada-women-bag/2.webp",
                            "https://cdn.dummyjson.com/product-images/womens-bags/prada-women-bag/3.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("White Faux Leather Backpack")
                        .description("The White Faux Leather Backpack is a trendy and practical backpack for the modern woman. With a sleek white design and ample storage space, it's perfect for both casual and on-the-go styles.")
                        .category("womens-bags")
                        .price(BigDecimal.valueOf(39.99))
                        .discountPercentage(15.2)
                        .rating(3.36)
                        .stock(39)
                        .thumbnail("https://cdn.dummyjson.com/product-images/womens-bags/white-faux-leather-backpack/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/womens-bags/white-faux-leather-backpack/1.webp",
                            "https://cdn.dummyjson.com/product-images/womens-bags/white-faux-leather-backpack/2.webp",
                            "https://cdn.dummyjson.com/product-images/womens-bags/white-faux-leather-backpack/3.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Women Handbag Black")
                        .description("The Women Handbag in Black is a classic and versatile accessory that complements various outfits. With a timeless black color and functional design, it's a must-have in every woman's wardrobe.")
                        .category("womens-bags")
                        .price(BigDecimal.valueOf(59.99))
                        .discountPercentage(11.63)
                        .rating(2.89)
                        .stock(11)
                        .thumbnail("https://cdn.dummyjson.com/product-images/womens-bags/women-handbag-black/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/womens-bags/women-handbag-black/1.webp",
                            "https://cdn.dummyjson.com/product-images/womens-bags/women-handbag-black/2.webp",
                            "https://cdn.dummyjson.com/product-images/womens-bags/women-handbag-black/3.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Black Women's Gown")
                        .description("The Black Women's Gown is an elegant and timeless evening gown. With a sleek black design, it's perfect for formal events and special occasions, exuding sophistication and style.")
                        .category("womens-dresses")
                        .price(BigDecimal.valueOf(129.99))
                        .discountPercentage(10.48)
                        .rating(3.64)
                        .stock(25)
                        .thumbnail("https://cdn.dummyjson.com/product-images/womens-dresses/black-women's-gown/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/womens-dresses/black-women's-gown/1.webp",
                            "https://cdn.dummyjson.com/product-images/womens-dresses/black-women's-gown/2.webp",
                            "https://cdn.dummyjson.com/product-images/womens-dresses/black-women's-gown/3.webp",
                            "https://cdn.dummyjson.com/product-images/womens-dresses/black-women's-gown/4.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Corset Leather With Skirt")
                        .description("The Corset Leather With Skirt is a bold and edgy ensemble that combines a stylish corset with a matching skirt. Ideal for fashion-forward individuals, it makes a statement at any event.")
                        .category("womens-dresses")
                        .price(BigDecimal.valueOf(89.99))
                        .discountPercentage(16.26)
                        .rating(3.05)
                        .stock(30)
                        .thumbnail("https://cdn.dummyjson.com/product-images/womens-dresses/corset-leather-with-skirt/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/womens-dresses/corset-leather-with-skirt/1.webp",
                            "https://cdn.dummyjson.com/product-images/womens-dresses/corset-leather-with-skirt/2.webp",
                            "https://cdn.dummyjson.com/product-images/womens-dresses/corset-leather-with-skirt/3.webp",
                            "https://cdn.dummyjson.com/product-images/womens-dresses/corset-leather-with-skirt/4.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Corset With Black Skirt")
                        .description("The Corset With Black Skirt is a chic and versatile outfit that pairs a fashionable corset with a classic black skirt. It offers a trendy and coordinated look for various occasions.")
                        .category("womens-dresses")
                        .price(BigDecimal.valueOf(79.99))
                        .discountPercentage(15.06)
                        .rating(4.52)
                        .stock(33)
                        .thumbnail("https://cdn.dummyjson.com/product-images/womens-dresses/corset-with-black-skirt/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/womens-dresses/corset-with-black-skirt/1.webp",
                            "https://cdn.dummyjson.com/product-images/womens-dresses/corset-with-black-skirt/2.webp",
                            "https://cdn.dummyjson.com/product-images/womens-dresses/corset-with-black-skirt/3.webp",
                            "https://cdn.dummyjson.com/product-images/womens-dresses/corset-with-black-skirt/4.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Dress Pea")
                        .description("The Dress Pea is a stylish and comfortable dress with a pea pattern. Perfect for casual outings, it adds a playful and fun element to your wardrobe, making it a great choice for day-to-day wear.")
                        .category("womens-dresses")
                        .price(BigDecimal.valueOf(49.99))
                        .discountPercentage(17.68)
                        .rating(4.88)
                        .stock(6)
                        .thumbnail("https://cdn.dummyjson.com/product-images/womens-dresses/dress-pea/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/womens-dresses/dress-pea/1.webp",
                            "https://cdn.dummyjson.com/product-images/womens-dresses/dress-pea/2.webp",
                            "https://cdn.dummyjson.com/product-images/womens-dresses/dress-pea/3.webp",
                            "https://cdn.dummyjson.com/product-images/womens-dresses/dress-pea/4.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Marni Red & Black Suit")
                        .description("The Marni Red & Black Suit is a sophisticated and fashion-forward suit ensemble. With a combination of red and black tones, it showcases a modern design for a bold and confident look.")
                        .category("womens-dresses")
                        .price(BigDecimal.valueOf(179.99))
                        .discountPercentage(19.02)
                        .rating(4.48)
                        .stock(62)
                        .thumbnail("https://cdn.dummyjson.com/product-images/womens-dresses/marni-red-&-black-suit/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/womens-dresses/marni-red-&-black-suit/1.webp",
                            "https://cdn.dummyjson.com/product-images/womens-dresses/marni-red-&-black-suit/2.webp",
                            "https://cdn.dummyjson.com/product-images/womens-dresses/marni-red-&-black-suit/3.webp",
                            "https://cdn.dummyjson.com/product-images/womens-dresses/marni-red-&-black-suit/4.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Green Crystal Earring")
                        .description("The Green Crystal Earring is a dazzling accessory that features a vibrant green crystal. With a classic design, it adds a touch of elegance to your ensemble, perfect for formal or special occasions.")
                        .category("womens-jewellery")
                        .price(BigDecimal.valueOf(29.99))
                        .discountPercentage(15.24)
                        .rating(3.96)
                        .stock(54)
                        .thumbnail("https://cdn.dummyjson.com/product-images/womens-jewellery/green-crystal-earring/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/womens-jewellery/green-crystal-earring/1.webp",
                            "https://cdn.dummyjson.com/product-images/womens-jewellery/green-crystal-earring/2.webp",
                            "https://cdn.dummyjson.com/product-images/womens-jewellery/green-crystal-earring/3.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Green Oval Earring")
                        .description("The Green Oval Earring is a stylish and versatile accessory with a unique oval shape. Whether for casual or dressy occasions, its green hue and contemporary design make it a standout piece.")
                        .category("womens-jewellery")
                        .price(BigDecimal.valueOf(24.99))
                        .discountPercentage(15.18)
                        .rating(3.57)
                        .stock(73)
                        .thumbnail("https://cdn.dummyjson.com/product-images/womens-jewellery/green-oval-earring/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/womens-jewellery/green-oval-earring/1.webp",
                            "https://cdn.dummyjson.com/product-images/womens-jewellery/green-oval-earring/2.webp",
                            "https://cdn.dummyjson.com/product-images/womens-jewellery/green-oval-earring/3.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Tropical Earring")
                        .description("The Tropical Earring is a fun and playful accessory inspired by tropical elements. Featuring vibrant colors and a lively design, it's perfect for adding a touch of summer to your look.")
                        .category("womens-jewellery")
                        .price(BigDecimal.valueOf(19.99))
                        .discountPercentage(0.76)
                        .rating(4.4)
                        .stock(1)
                        .thumbnail("https://cdn.dummyjson.com/product-images/womens-jewellery/tropical-earring/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/womens-jewellery/tropical-earring/1.webp",
                            "https://cdn.dummyjson.com/product-images/womens-jewellery/tropical-earring/2.webp",
                            "https://cdn.dummyjson.com/product-images/womens-jewellery/tropical-earring/3.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Black & Brown Slipper")
                        .description("The Black & Brown Slipper is a comfortable and stylish choice for casual wear. Featuring a blend of black and brown colors, it adds a touch of sophistication to your relaxation.")
                        .category("womens-shoes")
                        .price(BigDecimal.valueOf(19.99))
                        .discountPercentage(3.33)
                        .rating(2.53)
                        .stock(3)
                        .thumbnail("https://cdn.dummyjson.com/product-images/womens-shoes/black-&-brown-slipper/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/womens-shoes/black-&-brown-slipper/1.webp",
                            "https://cdn.dummyjson.com/product-images/womens-shoes/black-&-brown-slipper/2.webp",
                            "https://cdn.dummyjson.com/product-images/womens-shoes/black-&-brown-slipper/3.webp",
                            "https://cdn.dummyjson.com/product-images/womens-shoes/black-&-brown-slipper/4.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Calvin Klein Heel Shoes")
                        .description("Calvin Klein Heel Shoes are elegant and sophisticated, designed for formal occasions. With a classic design and high-quality materials, they complement your stylish ensemble.")
                        .category("womens-shoes")
                        .price(BigDecimal.valueOf(79.99))
                        .discountPercentage(3.19)
                        .rating(4.92)
                        .stock(93)
                        .thumbnail("https://cdn.dummyjson.com/product-images/womens-shoes/calvin-klein-heel-shoes/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/womens-shoes/calvin-klein-heel-shoes/1.webp",
                            "https://cdn.dummyjson.com/product-images/womens-shoes/calvin-klein-heel-shoes/2.webp",
                            "https://cdn.dummyjson.com/product-images/womens-shoes/calvin-klein-heel-shoes/3.webp",
                            "https://cdn.dummyjson.com/product-images/womens-shoes/calvin-klein-heel-shoes/4.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Golden Shoes Woman")
                        .description("The Golden Shoes for Women are a glamorous choice for special occasions. Featuring a golden hue and stylish design, they add a touch of luxury to your outfit.")
                        .category("womens-shoes")
                        .price(BigDecimal.valueOf(49.99))
                        .discountPercentage(13.93)
                        .rating(3.26)
                        .stock(88)
                        .thumbnail("https://cdn.dummyjson.com/product-images/womens-shoes/golden-shoes-woman/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/womens-shoes/golden-shoes-woman/1.webp",
                            "https://cdn.dummyjson.com/product-images/womens-shoes/golden-shoes-woman/2.webp",
                            "https://cdn.dummyjson.com/product-images/womens-shoes/golden-shoes-woman/3.webp",
                            "https://cdn.dummyjson.com/product-images/womens-shoes/golden-shoes-woman/4.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Pampi Shoes")
                        .description("Pampi Shoes offer a blend of comfort and style for everyday use. With a versatile design, they are suitable for various casual occasions, providing a trendy and relaxed look.")
                        .category("womens-shoes")
                        .price(BigDecimal.valueOf(29.99))
                        .discountPercentage(14.14)
                        .rating(3.05)
                        .stock(49)
                        .thumbnail("https://cdn.dummyjson.com/product-images/womens-shoes/pampi-shoes/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/womens-shoes/pampi-shoes/1.webp",
                            "https://cdn.dummyjson.com/product-images/womens-shoes/pampi-shoes/2.webp",
                            "https://cdn.dummyjson.com/product-images/womens-shoes/pampi-shoes/3.webp",
                            "https://cdn.dummyjson.com/product-images/womens-shoes/pampi-shoes/4.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Red Shoes")
                        .description("The Red Shoes make a bold statement with their vibrant red color. Whether for a party or a casual outing, these shoes add a pop of color and style to your wardrobe.")
                        .category("womens-shoes")
                        .price(BigDecimal.valueOf(34.99))
                        .discountPercentage(17.69)
                        .rating(3.25)
                        .stock(7)
                        .thumbnail("https://cdn.dummyjson.com/product-images/womens-shoes/red-shoes/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/womens-shoes/red-shoes/1.webp",
                            "https://cdn.dummyjson.com/product-images/womens-shoes/red-shoes/2.webp",
                            "https://cdn.dummyjson.com/product-images/womens-shoes/red-shoes/3.webp",
                            "https://cdn.dummyjson.com/product-images/womens-shoes/red-shoes/4.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("IWC Ingenieur Automatic Steel")
                        .description("The IWC Ingenieur Automatic Steel watch is a durable and sophisticated timepiece. With a stainless steel case and automatic movement, it combines precision and style for watch enthusiasts.")
                        .category("womens-watches")
                        .price(BigDecimal.valueOf(4999.99))
                        .discountPercentage(9.45)
                        .rating(2.93)
                        .stock(90)
                        .thumbnail("https://cdn.dummyjson.com/product-images/womens-watches/iwc-ingenieur-automatic-steel/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/womens-watches/iwc-ingenieur-automatic-steel/1.webp",
                            "https://cdn.dummyjson.com/product-images/womens-watches/iwc-ingenieur-automatic-steel/2.webp",
                            "https://cdn.dummyjson.com/product-images/womens-watches/iwc-ingenieur-automatic-steel/3.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Rolex Cellini Moonphase")
                        .description("The Rolex Cellini Moonphase watch is a masterpiece of horology. Featuring a moon phase complication, it showcases the craftsmanship and elegance that Rolex is renowned for.")
                        .category("womens-watches")
                        .price(BigDecimal.valueOf(15999.99))
                        .discountPercentage(4.11)
                        .rating(3.83)
                        .stock(52)
                        .thumbnail("https://cdn.dummyjson.com/product-images/womens-watches/rolex-cellini-moonphase/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/womens-watches/rolex-cellini-moonphase/1.webp",
                            "https://cdn.dummyjson.com/product-images/womens-watches/rolex-cellini-moonphase/2.webp",
                            "https://cdn.dummyjson.com/product-images/womens-watches/rolex-cellini-moonphase/3.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Rolex Datejust Women")
                        .description("The Rolex Datejust Women's watch is an iconic timepiece designed for women. With a timeless design and a date complication, it offers both elegance and functionality.")
                        .category("womens-watches")
                        .price(BigDecimal.valueOf(10999.99))
                        .discountPercentage(15.94)
                        .rating(2.86)
                        .stock(4)
                        .thumbnail("https://cdn.dummyjson.com/product-images/womens-watches/rolex-datejust-women/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/womens-watches/rolex-datejust-women/1.webp",
                            "https://cdn.dummyjson.com/product-images/womens-watches/rolex-datejust-women/2.webp",
                            "https://cdn.dummyjson.com/product-images/womens-watches/rolex-datejust-women/3.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Watch Gold for Women")
                        .description("The Gold Women's Watch is a stunning accessory that combines luxury and style. Featuring a gold-plated case and a chic design, it adds a touch of glamour to any outfit.")
                        .category("womens-watches")
                        .price(BigDecimal.valueOf(799.99))
                        .discountPercentage(18.34)
                        .rating(4.24)
                        .stock(0)
                        .thumbnail("https://cdn.dummyjson.com/product-images/womens-watches/watch-gold-for-women/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/womens-watches/watch-gold-for-women/1.webp",
                            "https://cdn.dummyjson.com/product-images/womens-watches/watch-gold-for-women/2.webp",
                            "https://cdn.dummyjson.com/product-images/womens-watches/watch-gold-for-women/3.webp"
                        ))
                        .build(),

                    Product.builder()
                        .title("Women's Wrist Watch")
                        .description("The Women's Wrist Watch is a versatile and fashionable timepiece for everyday wear. With a comfortable strap and a simple yet elegant design, it complements various styles.")
                        .category("womens-watches")
                        .price(BigDecimal.valueOf(129.99))
                        .discountPercentage(12.6)
                        .rating(3.52)
                        .stock(12)
                        .thumbnail("https://cdn.dummyjson.com/product-images/womens-watches/women's-wrist-watch/thumbnail.webp")
                        .images(List.of(
                            "https://cdn.dummyjson.com/product-images/womens-watches/women's-wrist-watch/1.webp",
                            "https://cdn.dummyjson.com/product-images/womens-watches/women's-wrist-watch/2.webp",
                            "https://cdn.dummyjson.com/product-images/womens-watches/women's-wrist-watch/3.webp"
                        ))
                        .build()
                );

                productRepository.saveAll(products);
                System.out.println("Carga de datos completada: " + products.size() + " productos insertados.");
            }
        };
    }
}