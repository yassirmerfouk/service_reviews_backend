package com.ym;

import com.ym.model.Review;
import com.ym.repository.ReviewRepository;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableRabbit
@EnableFeignClients
public class ReviewsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewsMicroserviceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			ReviewRepository reviewRepository
	) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				if (reviewRepository.findAll().isEmpty()) {
					reviewRepository.save(
							Review.builder()
									.comment("On a passé une très bonne soirée. Très bonne ambiance, tres bonne cuisine et excellent service. On recommande.")
									.grade(5)
									.personnelAccountId(2L)
									.serviceId(1L)
									.build()
					);
					reviewRepository.save(
							Review.builder()
									.comment("Un lieu chaleureux, une cuisine raffinée qui enchante, un service professionnel et attentionné, le tout dans une ambiance festive parfaitement orchestrée pour les amateurs de musique et de belles discussions. C'est un véritable refuge, une deuxième maison où chaque détail sublime l'expérience.")
									.grade(5)
									.personnelAccountId(3L)
									.serviceId(1L)
									.build()
					);
					reviewRepository.save(
							Review.builder()
									.comment("Super restau ! C'était vraiment trop bon ! Le poisson est frais et délicieux. On s'est régalé !!!!!")
									.grade(4)
									.personnelAccountId(4L)
									.serviceId(1L)
									.build()
					);
					reviewRepository.save(
							Review.builder()
									.comment("Franchement, le service était médiocre, la bouffe était horrible sauf l’eau qui était très bonne et le musicien, je pense que ce restaurant fermera ses portes bientôt. N’y allez pas.")
									.grade(1)
									.personnelAccountId(5L)
									.serviceId(1L)
									.build()
					);

					reviewRepository.save(
							Review.builder()
									.comment("De retour sur Casablanca, je suis passé au Sofitel où le restaurant oriental est ouvert de nouveau après une trop longue pause lié au covid. Je tiens à remercier Hind pour son accueil et le soin qu’elle apporte aux clients. Je reviendrai avec plaisir !")
									.grade(5)
									.personnelAccountId(2L)
									.serviceId(2L)
									.build()
					);
					reviewRepository.save(
							Review.builder()
									.comment("Avec son acceuil charmant et attentionné, l atelier oriental offre un cadre intimiste et relaxant pour le voyageur. Les plats marocains (ou libanais) sont très bon, et bien accompagné par un vin local de très bonne facture.")
									.grade(4)
									.personnelAccountId(3L)
									.serviceId(2L)
									.build()
					);
					reviewRepository.save(
							Review.builder()
									.comment("Tres satisfait avec Mr SAAD et l'equipe du restaurant. Suite a notre sejours de six jours a l'hotel sofitel de Casablanca. Dans le cadre de l'assemblee generale constitutive de confederation Africaine de lindrustrie de cuirs en afrique.")
									.grade(5)
									.personnelAccountId(4L)
									.serviceId(2L)
									.build()
					);

					reviewRepository.save(
							Review.builder()
									.comment("J’ai passé de superbe vacances en compagnie des animateurs et du contexte !!!a refaire et merci…\n" +
											"Les sorties étaient vraiment superbes et au niveau de la nourriture rien à dire ,la sociabilité des maîtres nageurs étaient magiques merci merci et merci le Maroc")
									.grade(5)
									.personnelAccountId(2L)
									.serviceId(3L)
									.build()
					);
					reviewRepository.save(
							Review.builder()
									.comment("Un joli hôtel tous est bien\n" +
											"La nourriture super bonne avec un choix divers pour tous les goût on remercie le chef de cuisine\n" +
											"Le service restauration nickel rien a dire une super note pour les serveurs\n" +
											"L'hôtel est a une demi. Heure de Marrakech en taxi\n" +
											"La vue montagnes et super\n" +
											"L'animation chaque jour plusieurs activités merci beaucoup a Oussama, sam et safa")
									.grade(5)
									.personnelAccountId(3L)
									.serviceId(3L)
									.build()
					);


				}
			}
		};
	}
}
