package com.app;

import com.app.model.Category;
import com.app.model.Service;
import com.app.model.ServiceImage;
import com.app.repository.CategoryRepository;
import com.app.repository.ServiceImageRepository;
import com.app.repository.ServiceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class ServicesMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicesMicroserviceApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(
            CategoryRepository categoryRepository,
            ServiceRepository serviceRepository,
            ServiceImageRepository serviceImageRepository
    ){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                if(categoryRepository.findAll().isEmpty()) {
                    Category category1 = categoryRepository.save(
                            Category.builder()
                                    .name("Web Sites")
                                    .description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
                                    .build()
                    );
                    Category category2 = categoryRepository.save(
                            Category.builder()
                                    .name("Restaurants")
                                    .description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
                                    .build()
                    );
                    Category category3 = categoryRepository.save(
                            Category.builder()
                                    .name("Hotels")
                                    .description("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
                                    .build()
                    );

                    if (serviceRepository.findAll().isEmpty()) {
                        Service service1 = serviceRepository.save(
                                Service.builder()
                                        .businessAccountId(6L)
                                        .contactEmail("contact@elgousto.com")
                                        .contactPhone("0522 36 57 57 - 0661 33 36 85")
                                        .longDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
                                        .name("Restaurant El Gousto")
                                        .reviewsAverage(3.75)
                                        .reviewsNumbers(4)
                                        .shortDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
                                        .category(category2)
                                        .build()
                        );
                        serviceImageRepository.save(
                                ServiceImage.builder()
                                        .storageName("https://res.cloudinary.com/djcdpsyie/image/upload/v1707762718/atelier-oriental_pcpwrj.jpg")
                                        .service(service1)
                                        .build()
                        );
                        Service service2 = serviceRepository.save(
                                Service.builder()
                                        .businessAccountId(6L)
                                        .contactEmail("atelieroriental@mail.com")
                                        .contactPhone("+212 5224-56200")
                                        .longDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
                                        .name("Atelier Oriental")
                                        .reviewsAverage(4.66)
                                        .reviewsNumbers(3)
                                        .shortDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")
                                        .category(category2)
                                        .build()
                        );
                        serviceImageRepository.save(
                                ServiceImage.builder()
                                        .storageName("https://res.cloudinary.com/djcdpsyie/image/upload/v1707762726/salle-principale_k1fqfh.jpg")
                                        .service(service2)
                                        .build()
                        );
                        Service service3 = serviceRepository.save(
                                Service.builder()
                                        .businessAccountId(7L)
                                        .contactEmail("aquafun-resa@pickalbatros-morocco.com")
                                        .contactPhone("+212 5 24 48 77 00 / +212 5 24 48 77 77")
                                        .longDescription("Situé à 18 km de Marrakech, l’Aqua Fun Club s’étend sur un domaine de 8 hectares abritant le plus grand parc aquatique du Maroc, avec pas moins de 57 toboggans, idéal pour un plein de sensations fortes en famille ou entre amis.\n" +
                                                "\n" +
                                                "L’Aqua Fun Club dispose de 262 chambres confortables et bien équipées, dont 107 chambres familiales, 149 chambres standard, 5 suites junior et 1 suite royale. Elles sont climatisées, comprennent un coin salon avec un balcon privé donnant sur la piscine ou sur le jardin et\n" +
                                                "\n" +
                                                "disposent d’un minibar, d’un coffre-fort, d’un téléphone, d’une TV satellite et de connexion Wi-Fi. Les salles de bains sont équipées de douche ou baignoire, de sèche-cheveux et d’articles de toilette.\n" +
                                                "\n" +
                                                "Côté restauration, l’Aqua Fun Club propose une sélection de cuisines variées servies dans différents restaurants : l’Asiatique, le Mediterranean, le Pizza Pino et le Tajine. Vous pourrez également prendre un verre aux bars de l’hôtel ou profiter de sa discothèque privée.\n" +
                                                "\n" +
                                                "L’hôtel dispose également de plusieurs autres équipements et services, dont un spa, un court de tennis, un terrain mini foot, mini golf, un club pour enfants, la connexion Wi-Fi et un service de\n" +
                                                "\n" +
                                                "navette gratuite.\n" +
                                                "\n" +
                                                "Les jeux aquatiques de l’Aqua Park se déclinent à l'infini: toboggans, pistes d'eau, piscine à vagues, arbre à eau, immense espace de jeux d'eau suspendu, pataugeoires pour enfants et bien d’autres surprises vous attendent....")
                                        .name("Aqua Fun Marrakech")
                                        .reviewsAverage(5)
                                        .reviewsNumbers(2)
                                        .shortDescription("Situé à 18 km de Marrakech, l’Aqua Fun Club s’étend sur un domaine de 8 hectares abritant le plus grand parc aquatique du Maroc, avec pas moins de 57 toboggans, idéal pour un plein de sensations fortes en famille ou entre amis.")
                                        .category(category3)
                                        .build()
                        );
                        serviceImageRepository.save(
                                ServiceImage.builder()
                                        .storageName("https://res.cloudinary.com/djcdpsyie/image/upload/v1707762740/aqua-park_vr8ex8.jpg")
                                        .service(service3)
                                        .build()
                        );

                    }

                }
            }
        };
    }
}
