package com.redgunner.bloggy.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.redgunner.bloggy.database.dao.ArticlesDao
import com.redgunner.bloggy.models.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/*
* build the Articles table or Articles database
*
* And add dummy data just to see how the app will look like
*
* */
@Database(entities = [Article::class], version = 1)
abstract class ArticlesDataBase : RoomDatabase() {


    abstract fun articleDAO(): ArticlesDao


    private class ArticleDatabaseCallBack() : RoomDatabase.Callback() {


        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val scope = CoroutineScope(SupervisorJob())


            Instance?.let { db ->


                scope.launch {
                    populateDatabase(db.articleDAO())
                }
            }

        }


        suspend fun populateDatabase(articlesDao: ArticlesDao) {

            articlesDao.deleteArticles()

            // Add dummy article to local database just for testing purposes

            //World
            val listOfWorldArticles = listOf(
                Article(
                    "Google outage: YouTube, Docs and Gmail knocked offline",
                    "World",
                    "Google applications including YouTube, email and Docs are suffering a rare service outage, with users unable to access many of the company's services.\n" +
                            "\n" +
                            "The outage started shortly before noon UK time, with Google sites returning server errors when visited.\n" +
                            "\n" +
                            "Users around the world reported problems with Gmail, Google Drive, the Android Play Store, Maps, and more.\n" +
                            "\n" +
                            "Google's search engine, however, remained unaffected by the problems affecting its other services.\n" +
                            "\n" +
                            "Despite the widespread outage, Google's service dashboard initially showed no errors - before switching to red status across all services.\n" +
                            "\n" +
                            "Many individuals and businesses rely on Google services for basic work apps such as email and calendars.\n" +
                            "\n" +
                            "Users of Google Docs could continue to work if they had synced documents offline, but were unable to use any online features.\n" +
                            "\n" +
                            "\"We're aware of a problem with Gmail affecting a majority of users. The affected users are unable to access Gmail,\" a statement said, with the word \"Gmail\" replaced by other services.\n" +
                            "\n" +
                            "Google has been contacted for comment, but one spokesperson said they were unable to access their email.",
                    "12 Mars",
                    false,
                    false,
                    "https://ichef.bbci.co.uk/news/976/cpsprodpb/1825A/production/_116060989_5dd578be-22b5-471c-b424-81e1689d570a.jpg",
                    "#06d6a0"
                ),
                Article(
                    "China warns of interference over Bloomberg journalist arrest",
                    "World",
                    "China has said the arrest of a journalist working for the newswire Bloomberg is an \"internal affair\", warning others not to interfere.\n" +
                            "\n" +
                            "Chinese citizen Haze Fan was detained last week, accused of endangering national security by authorities.\n" +
                            "\n" +
                            "It is the latest in a string of arrests or expulsions of journalists in China.\n" +
                            "\n" +
                            "The European Union (EU) responded by urging China to release all journalists held in connection with their reporting.\n" +
                            "\n" +
                            "In a statement released on Saturday, the EU said it expected Chinese authorities to grant Ms Fan \"medical assistance if needed, prompt access to a lawyer of her choice, and contacts with her family\".\n" +
                            "\n" +
                            "The Foreign Correspondents Club in China (FCCC) also expressed its solidarity, adding that international media depended on its Chinese staff.\n" +
                            "\n" +
                            "However, the Chinese Embassy in the EU responded on Sunday, saying Ms Fan was \"suspected of engaging in criminal activities that endanger China's national security and was recently taken into compulsory measures by the Beijing State Security Bureau in accordance with the law\".\n" +
                            "\n" +
                            "On its official WeChat account, it added that the case is currently being investigated in accordance with the law and that Ms Fan's rights are fully ensured. This is \"entirely an internal affair of China, and no other country or organization has the right to interfere\".",
                    "12 Mars",
                    false,
                    false,
                    "https://ichef.bbci.co.uk/news/976/cpsprodpb/1158F/production/_116055017_gettychineseflag.jpg",
                    "#06d6a0"
                ),
                Article(
                    "France could negotiate with 'enemy' forces in Sahel",
                    "World",
                    "France's chief of defence staff has not ruled out the idea of negotiating with hostile forces operating in Africa’s Sahel region.\n" +
                            "\n" +
                            "France, which has more than 5,000 troops in the region, has often stated publicly that there must be no negotiation with militant Islamist groups.\n" +
                            "\n" +
                            "However General François Lecointre told Radio France International that negotiating with the \"enemy\" was possible – although he stressed this was a decision for the politicians to make.\n" +
                            "\n" +
                            "In Mali – where jihadist attacks are common – the authorities have recently become open to the idea of dialogue in order to help end the escalating violence.\n" +
                            "\n" +
                            "In neighbouring Burkina Faso, President Marc Roch Kaboré has strongly opposed any negotiations.",
                    "12 Mars",
                    false,
                    false,
                    "https://ichef.bbci.co.uk/live-experience/cps/624/cpsprodpb/vivo/live/images/2020/12/14/4b88bed3-a26b-4ee9-8082-92cc93ed666d.jpg",
                    "#06d6a0"
                ),
                Article(
                    "Oil tanker BW Rhine hit by explosion at Saudi city of Jeddah",
                    "World",
                    "An oil tanker was \"hit from an external source\" while unloading in the Saudi city of Jeddah, causing an explosion and fire onboard, its owners have said.\n" +
                            "\n" +
                            "The Singapore-flagged BW Rhine's hull was damaged by the blast, but the crew put out the fire and no-one was injured, according to Hafnia.\n" +
                            "\n" +
                            "The firm added that it was possible some oil had escaped from the vessel.\n" +
                            "\n" +
                            "The incident comes two weeks after a blast damaged a Maltese-flagged tanker at another Saudi Red Sea port.\n" +
                            "\n" +
                            "A Saudi-led military coalition fighting Yemen's rebel Houthi movement said that vessel was hit by shrapnel in what it described as a foiled terrorist attack by an unmanned boat laden with explosives.\n" +
                            "\n" +
                            "The Houthis did not comment on the allegation, but days before they said they had fired a missile that struck a Saudi Aramco petroleum products distribution plant in Jeddah, causing a fire in a fuel tank.",
                    "12 Mars",
                    false,
                    false,
                    "https://ichef.bbci.co.uk/news/976/cpsprodpb/10DDD/production/_116058096_mediaitem116058092.jpg",
                    "#06d6a0"
                )
            )

            articlesDao.addArticles(listOfWorldArticles)

            //Business
            val listOfBusinessArticle = listOf(
                Article(
                    "Sizewell C: Government in talks to fund £20bn nuclear plant",
                    "Business",
                    "The government has begun talks with EDF about the construction of a new £20bn nuclear power plant in Suffolk.\n" +
                            "\n" +
                            "The Sizewell C site could generate 3.2 gigawatts of electricity, enough to provide 7% of the UK's energy needs.\n" +
                            "\n" +
                            "But it has proved controversial with campaigners saying it is \"ridiculously expensive\" and that taxpayers will have to foot the bill for extra costs.\n" +
                            "\n" +
                            "The government said any deal would be subject to approval on areas such as value for money and affordability.\n" +
                            "\n" +
                            "EDF, the French energy giant, is also building the Hinkley Point C nuclear energy plant in Somerset in partnership with China General Nuclear Power.\n" +
                            "\n" +
                            "The government said talks with EDF about Sizewell C would depend on the progress of the Hinkley Point C. However, that project is set to cost up to £2.9bn more than originally thought and will be up to 15 months late.\n" +
                            "\n" +
                            "China General Nuclear Power has a 20% stake in Sizewell C but is thought to be planning to pull out after security concerns were raised about a Chinese state-owned company designing and running its own design nuclear reactor on UK soil.\n" +
                            "\n" +
                            "If it does pull out, it would increase the need for new investors. One option could be for the government to take a stake in the plant.",
                    "12 april",
                    false,
                    false,
                    "https://ichef.bbci.co.uk/news/976/cpsprodpb/0C7F/production/_86099130_coverimageedfbetterquality.jpg",
                    "#ffadad"
                ),
                Article(
                    "Brexit: Pound gains after EU trade talks extended",
                    "Business",
                    "The pound has pushed higher against the US dollar and the euro on news that trade negotiations with the European Union (EU) are continuing.\n" +
                            "\n" +
                            "By midday in London, sterling was up 1.5% against the dollar at \$1.3428, while against the euro it rose 1.2% to €1.1050.\n" +
                            "\n" +
                            "The pound hit a one-month low last week on worries of a no-deal Brexit.\n" +
                            "\n" +
                            "Although the deadline was missed on Sunday, the EU and the UK agreed to continue negotiations.\n" +
                            "\n" +
                            "UK Prime Minister Boris Johnson and the European Commission President Ursula von der Leyen will carry on talks \"to see whether an agreement can even at this late stage be reached\".\n" +
                            "\n" +
                            "On the stock market, the FTSE 100 share index was up 33.06 points, or 0.5%, at 6,579.81. However, the FTSE 250 index, which contains more domestically-focused stocks, saw a bigger rise, climbing 1.8% to 19,982.58.",
                    "12 april",
                    false,
                    false,
                    "https://ichef.bbci.co.uk/news/976/cpsprodpb/7C01/production/_116054713_whatsubject.jpg",
                    "#ffadad"
                ),
                Article(
                    "Indonesia courts SpaceX as new rocket launch site",
                    "Business",
                    "Indonesia has put itself forward as a possible rocket launch site for Elon Musk's SpaceX venture.\n" +
                            "\n" +
                            "The south-east Asian country is already in talks with Mr Musk's Tesla about a possible electric battery partnership.\n" +
                            "\n" +
                            "According to Indonesia's Coordinating Ministry for Maritime and Investment Affairs, President Joko Widodo has been in discussions with Mr Musk.\n" +
                            "\n" +
                            "The Tesla billionaire is sending a team to Indonesia in January to look at potential investments, it added.\n" +
                            "\n" +
                            "One option to be discussed is building a plant in Central Java.\n" +
                            "\n" +
                            "Indonesia is home to large deposits of copper, nickel and tin, and aims to become the world's biggest producer of electric vehicle batteries.\n" +
                            "\n" +
                            "\"Nickel is the biggest challenge for high-volume, long-range batteries! Australia and Canada are doing pretty well. US nickel production is objectively very lame. Indonesia is great!\" Mr Musk tweeted earlier this year.",
                    "12 april",
                    false,
                    false,
                    "https://ichef.bbci.co.uk/news/976/cpsprodpb/06D1/production/_116054710_spacex.jpg",
                    "#ffadad"
                ),
                Article(
                    "Covid vaccines: Will drug companies make bumper profits?",
                    "Business",
                    "At the start of the pandemic, we were warned: it takes years to develop a vaccine, so don't expect too much too soon.\n" +
                            "\n" +
                            "Now, after only 10 months, the injections have begun and the firms behind the front-runners are household names.\n" +
                            "\n" +
                            "As a result, investment analysts are forecasting that at least two of them, American biotech company Moderna and Germany's BioNTech with its partner, US giant Pfizer, would be likely to make billions of dollars next year.\n" +
                            "\n" +
                            "But it's not clear how much vaccine makers really are set to cash in beyond that.\n" +
                            "\n" +
                            "Thanks to the way these vaccines have been funded and the number of firms joining the race to make them, any opportunity to make big profits could be short-lived.",
                    "12 april",
                    false,
                    false,
                    "https://ichef.bbci.co.uk/news/976/cpsprodpb/C047/production/_115332294_injectiongetty.jpg",
                    "#ffadad"
                )
            )

            articlesDao.addArticles(listOfBusinessArticle)

            //Tech
            val listOfTechArticles = listOf(
                Article(
                    "Cyberpunk 2077 makers apologise for game glitches",
                    "Tech",
                    "The makers of one of most anticipated games of the year have apologised and offered refunds amid a backlash from gamers about performance problems.\n" +
                            "\n" +
                            "Cyberpunk 2077, which stars Keanu Reeves, came out last week after several delays.\n" +
                            "\n" +
                            "Problems with glitches and crashing have mostly appeared on last generation consoles like the PS4 and Xbox One.\n" +
                            "\n" +
                            "CD Projekt Red says it \"should have paid more attention to making it play better\" on those consoles.\n" +
                            "\n" +
                            "They are now offering refunds to people who want to return the game.",
                    "04 december",
                    false,
                    false,
                    "https://ichef.bbci.co.uk/news/976/cpsprodpb/BA51/production/_115979674_screen-ambushed-en.jpg",
                    "#fb8500"
                ),
                Article(
                    "Tech Tent: Breaking up Facebook",
                    "Tech",
                    "In a landmark lawsuit, US regulators have accused Facebook of buying up rivals in order to stifle competition.\n" +
                            "\n" +
                            "They have made it clear they will seek a drastic remedy - the sale of Instagram and WhatsApp. On this week's Tech Tent we ask whether it is really likely that the social media giant's empire will be dismantled.New York Attorney General Letitia James could hardly have been clearer in her denunciation as she outlined the case she and more than 45 other state and federal regulators are bringing against Facebook.\n" +
                            "\n" +
                            "\"For nearly a decade, Facebook has used its dominance and monopoly power to crush smaller rivals, and snuff out competition, all at the expense of everyday users,\" she said.",
                    "04 december",
                    false,
                    false,
                    "https://ichef.bbci.co.uk/news/976/cpsprodpb/BB29/production/_116031974_zuck_assets2.jpg",
                    "#fb8500"
                ),
                Article(
                    "EA to buy Dirt Rally-maker Codemasters for £1bn",
                    "Tech",
                    "Games giant Electronic Arts is set to acquire the British video-game-maker Codemasters, in a deal worth an estimated \$1.2bn (£1bn).\n" +
                            "\n" +
                            "Codemasters is best known for its racing games, including the Dirt Rally series and Formula 1 licences. EA owns the global franchise to Need for Speed.\n" +
                            "\n" +
                            "Codemasters had said it would recommend its shareholders accept an earlier offer, by one of EA's main rivals.\n" +
                            "\n" +
                            "New-York- based Take-Two Interactive had offered a package worth \$973m.\n" +
                            "\n" +
                            "Codemasters' senior management team, including the chief executive and chief financial officer, would remain with the company during the transition to EA, with the deal expected to be completed by the end of March 2021, it said.\n" +
                            "\n" +
                            "Chairman Gerhard Florin said the company would \"benefit from EA's knowledge, resources and extensive global scale - both overall and specifically within the racing sector\".\n" +
                            "\n" +
                            "Founded in 1986 by brothers David and Richard Darling, Codemasters is headquartered in Warwickshire. EA is based in California.",
                    "04 december",
                    false,
                    false,
                    "https://ichef.bbci.co.uk/news/976/cpsprodpb/17B65/production/_115252179_9aea343d-7e76-4aa5-a2ba-998c9ad07143.jpg",
                    "#fb8500"
                ),
                Article(
                    "Odeon cinema owner warns it's running out of cash",
                    "Tech",
                    "AMC Entertainment Holdings, the world's largest movie theatre operator, has secured \$100m (£76m) in emergency funds - but warned the money will only help it through another month.\n" +
                            "\n" +
                            "The firm, owner of the UK's Odeon Cinemas, said attendance has dropped 92% in the US and 86% internationally.\n" +
                            "\n" +
                            "It is burning through \$125m a month as concerns about Covid-19 shut some cinemas and keep audiences away.\n" +
                            "\n" +
                            "Even after reopening, it said the growth of streaming posed a challenge.\n" +
                            "\n" +
                            "Warner Bros this month said it planned to release all of its 2021 films online on the same day they are released to cinemas. They will air on the HBO Max streaming service, with which it shares a parent company.\n" +
                            "\n" +
                            "Disney has also said it plans to push more of its films to its Disney+ streaming service.\n" +
                            "\n" +
                            "AMC said those decisions may prompt other studios to adopt similar strategies.\n" +
                            "\n" +
                            "\"These practices have significantly impacted our revenues and are expected to continue to have an adverse impact on our business and results of operations going forward,\" it said in a US regulatory filing that accompanied the funding announcement.",
                    "04 december",
                    false,
                    false,
                    "https://ichef.bbci.co.uk/news/976/cpsprodpb/0CB1/production/_115794230_tom-and-jerry.jpg",
                    "#fb8500"
                )
            )

            articlesDao.addArticles(listOfTechArticles)

            //Science
            val listOfScienceArticles = listOf(
                Article(
                    "'Not enough' climate ambition shown by leaders",
                    "Science",
                    "The UK minister tasked with leading UN climate talks says world leaders are failing to show the necessary level of ambition.\n" +
                            "\n" +
                            "Alok Sharma was speaking at the conclusion of a virtual climate summit organised by the UK, UN and France.\n" +
                            "\n" +
                            "He said \"real progress\" had been made and 45 countries had put forward new climate plans for 2030.\n" +
                            "\n" +
                            "But these were not enough to prevent dangerous warming this century, Mr Sharma explained.\n" +
                            "\n" +
                            "Taking place on the fifth anniversary of the Paris climate agreement, the summit heard the UN Secretary General warn that every country needed to declare a climate emergency.\n" +
                            "\n" +
                            "Around 70 heads of state and government took part in the meeting, which was organised by the UK, UN and France. They outlined new pledges and commitments to curb carbon.\n" +
                            "\n" +
                            "China's contribution was eagerly awaited, not just because it is the world's biggest emitter, but because it has recently promised to reach net zero emissions by 2060.",
                    "30 november",
                    false,
                    false,
                    "https://ichef.bbci.co.uk/news/976/cpsprodpb/13D2/production/_116047050_064314884.jpg",
                    "#7400b8"
                ),
                Article(
                    "'Dog kennel' satellite returns first ocean observations",
                    "Science",
                    "The new satellite that will become the primary orbital tool for tracking sea-level rise is in excellent shape.\n" +
                            "\n" +
                            "Sentinel-6 \"Michael Freilich\" was only launched three weeks ago, but already it is mapping ocean features in exquisite detail.\n" +
                            "\n" +
                            "The dog kennel-shaped spacecraft is a joint endeavour between Europe and the US.\n" +
                            "\n" +
                            "It is the latest iteration in a series of missions that have been measuring sea-surface height going back to 1992.\n" +
                            "\n" +
                            "These earlier satellites have shown unequivocally that the oceans globally are rising at a rate in excess of 3mm per year over the 28-year period, with an acceleration apparent in the last decade.",
                    "30 november",
                    false,
                    false,
                    "https://ichef.bbci.co.uk/news/976/cpsprodpb/E660/production/_115667985_copernicus_sentinel-6_radar_altimeter.jpg",
                    "#7400b8"
                ), Article(
                    "China's Chang'e-5 mission leaves Moon's surface",
                    "Science",
                    "China has executed the next stage of its Chang'e-5 Moon mission, blasting into orbit samples gathered on the lunar surface.\n" +
                            "\n" +
                            "Right on cue, at 15:10 GMT, an ascent vehicle lit its engine to head up to a service module that will shepherd home the rock and dust materials.\n" +
                            "\n" +
                            "It's more than 40 years since lunar samples were last brought back to Earth.\n" +
                            "\n" +
                            "Chang'e-5 still has some key steps to negotiate to achieve mission success.\n" +
                            "\n" +
                            "The ascender has to rendezvous with the orbiter, and also pass over the samples.\n" +
                            "\n" +
                            "These will be enclosed in a module that will be aimed at Inner Mongolia. This module will have to survive a fiery, high-speed descent through Earth's atmosphere to get to the ground.",
                    "30 november",
                    false,
                    false,
                    "https://ichef.bbci.co.uk/news/976/cpsprodpb/A5A0/production/_115800424_lunarliftoff.jpg",
                    "#7400b8"
                ), Article(
                    "The woman behind China's Chang'e-5 Moon mission",
                    "Science",
                    "A 24-year-old female space commander has become a viral sensation on Chinese social media for her work on the Chang'e-5 Moon exploration programme.\n" +
                            "\n" +
                            "Despite being the youngest commander at the Wenchang Spacecraft Launch Site, Zhou Chengyu is known at work as \"Big Sister\" as a sign of respect.\n" +
                            "\n" +
                            "The Chang'e-5 mission is China's third successful Moon landing in seven years.\n" +
                            "\n" +
                            "Ms Zhou was in charge of the rocket connector system - described as a pivotal role." +
                            "The young astronaut has been a trending topic on Weibo since Chinese state media highlighted her as one of the women involved in the successful launch of the Chang'e-5 lunar probe on 23 November.\n" +
                            "\n" +
                            "Her story in particular has resonated with the public given her young age. Social media users have been celebrating her \"brilliance\" and referring to her as a \"source of pride\" for the country.",
                    "30 november",
                    false,
                    false,
                    "https://ichef.bbci.co.uk/news/976/cpsprodpb/76D0/production/_115961403_people'sdailyimage1.jpg",
                    "#7400b8"
                )
            )

            articlesDao.addArticles(listOfScienceArticles)

            //Health
            val listOfHealthArticles = listOf(

                Article(
                    "Covid-19: Trump rejects plan for early vaccines at White House",
                    "Health",
                    "US President Donald Trump says he has reversed a plan for White House officials to receive a coronavirus vaccine in the coming days.\n" +
                            "\n" +
                            "Officials said senior members of the Trump administration would be among the first to get the Pfizer/BioNTech jab.\n" +
                            "\n" +
                            "But Mr Trump later tweeted that people working at the White House \"should receive the vaccine somewhat later... unless specifically necessary\".\n" +
                            "\n" +
                            "The US will begin its roll out of the vaccine on Monday.\n" +
                            "\n" +
                            "The vaccine offers up to 95% protection against Covid-19.\n" +
                            "\n" +
                            "The first three million doses are being distributed to dozens of locations in all 50 states across the US. The first shipment of those doses left a facility in Michigan on Sunday, with health workers and the elderly in line to receive the first shots.",
                    "1 january",
                    false,
                    false,
                    "https://ichef.bbci.co.uk/news/976/cpsprodpb/14659/production/_116054538_064793774-1.jpg",
                    "#52b788"
                ),
                Article(
                    "Is the Covid vaccine safe?",
                    "Health",
                    "UK hospitals have started giving people the first doses of the Pfizer/BioNtech coronavirus vaccine.\n" +
                            "\n" +
                            "While many people want an injection as soon as possible, others are worried about putting something unknown into their bodies.\n" +
                            "\n" +
                            "How do we know a vaccine is safe?\n" +
                            "Safety trials begin in the lab, with tests and research on cells and animals, before moving on to human studies.\n" +
                            "\n" +
                            "The principle is to start small and only move to the next stage of testing if there are no outstanding safety concerns.",
                    "1 january",
                    false,
                    false,
                    "https://ichef.bbci.co.uk/news/976/cpsprodpb/2280/production/_115623880_yellow.jpg",
                    "#52b788"
                ),
                Article(
                    "Covid in Scotland: First care home resident receives vaccine",
                    "Health",
                    "A 90-year-old woman in South Lanarkshire has become the first care home resident to receive the Pfizer Covid-19 vaccine.\n" +
                            "\n" +
                            "Former carer Annie Innes was immunised at Abercorn House in Hamilton.\n" +
                            "\n" +
                            "Care home residents across Scotland have been prioritised to receive the vaccine, along with frontline health care staff.\n" +
                            "\n" +
                            "Ms Innes told reporters it was \"wonderful\" to get the vaccine just before Christmas.\n" +
                            "\n" +
                            "\"I hope it keeps me, my friends here and the staff safe and means we can get back to normal very soon,\" she said.\n" +
                            "\n" +
                            "She added: \"The nurses and the care home staff have been great with us and we are relieved to have been offered the vaccine.\"",
                    "1 january",
                    false,
                    false,
                    "https://ichef.bbci.co.uk/news/976/cpsprodpb/5BC7/production/_116059432_hi064799586-1.jpg",
                    "#52b788"
                )
            )

            articlesDao.addArticles(listOfHealthArticles)

            //Entertainment
            val listOfEntertainmentArticles = listOf(
                Article(
                    "The Prom: Will Gompertz reviews film with James Corden, Meryl Streep & Nicole Kidman",
                    "Entertainment",
                    "As you probably know, Ryan Murphy is the creative brains behind Glee. Ergo, he knows how to do feel-good musical comedy-drama set in a high school, which tackles social issues.\n" +
                            "\n" +
                            "It's his genre.\n" +
                            "\n" +
                            "No surprise then, that he's at the helm for the film adaptation of the Broadway musical The Prom, a feel-good musical comedy-drama set in a high school, which tackles social issues (homophobia, in this instance).\n" +
                            "\n" +
                            "To accompany his stellar creds is an all-star class featuring Meryl Streep (in full Mamma Mia mode), Nicole Kidman and James Corden (without a cat's tail this time, but with a camp American accent, which is only slightly less incongruous). !",
                    "8 june",
                    false,
                    false,
                    "https://ichef.bbci.co.uk/news/976/cpsprodpb/E33D/production/_116037185_will-prom-3-2.jpg",
                    "#f94144"
                ),
                Article(
                    "Bristol Banksy house owners 'have not pulled out of sale'",
                    "Entertainment",
                    "The owner of a house on which a Banksy mural appeared has said the sale of the property will still go ahead, despite reports they had pulled out.\n" +
                            "\n" +
                            "Nick Makin said it was \"not true\" that his mother, Aileen Makin, had withdrawn the house in Bristol from the market.\n" +
                            "\n" +
                            "\"When you wake up to tabloids saying the house is now worth £5m you've got to think about what you're doing... but it's not changing anything,\" he said.\n" +
                            "\n" +
                            "He added that he wanted the artwork to remain in place and be protected.\n" +
                            "\n" +
                            "Mr Makin told BBC Radio Bristol the sale had been \"put on hold for 48 hours\" after the mural appeared on the side of the semi-detached house in Totterdown on Thursday.",
                    "8 june",
                    false,
                    false,
                    "https://ichef.bbci.co.uk/news/976/cpsprodpb/12654/production/_115984357_5ffb2549-86c0-4775-887b-32cc9710fb11.jpg",
                    "#f94144"
                ),
                Article(
                    "How New Zealand's film industry boomed during the pandemic",
                    "Entertainment",
                    "It might be found at the bottom of the globe, but New Zealand has been at the top of the movie industry in 2020.\n" +
                            "\n" +
                            "Thanks to its handling of the Covid-19 pandemic, the country is enjoying an unprecedented boom in film production, with directors seeking safe conditions, and that most elusive thing this year - a normal life.\n" +
                            "\n" +
                            "International blockbusters including James Cameron's Avatar sequels, Amazon's Lord of the Rings series and Jane Campion's The Power of the Dog - starring Benedict Cumberbatch and Kirsten Dunst - all managed complex film shoots in New Zealand this year.\n" +
                            "\n" +
                            "The country's home-grown movies have also received a boost at the box office by Kiwis supporting their industry, leading one local filmmaker to joke that they're now living in \"the Hollywood of the Pacific\".\n" +
                            "\n" +
                            "\"We're in this sweet spot,\" says Curtis Vowell, the director of a comedy, Baby Done, that was executive produced by Oscar-winning Jojo Rabbit director Taika Waititi.\n" +
                            "\n" +
                            "\"We're lucky that we're isolated from the rest of the world, and I feel we've got a government that's been super helpful to the film industry. We're in the Hollywood of the Pacific, and it's almost too easy to take for granted how lucky we are.\n" +
                            "\n" +
                            "\"All my film crew friends have unlimited work, there's thousands of Kiwis who are fully employed. International tourism is awful, and the airlines are really struggling, but the film industry in New Zealand is thriving, which goes against all predictions.\"",
                    "8 june",
                    false,
                    false,
                    "https://ichef.bbci.co.uk/news/976/cpsprodpb/1528D/production/_115996668_119984326_158000115951298_9187571052559397636_n.jpg",
                    "#f94144"
                ),
                Article(
                    "Interior Chinatown: The novel taking on Hollywood’s Asian tropes",
                    "Entertainment",
                    "Willis Wu is not a Kung Fu Guy.\n" +
                            "\n" +
                            "Instead, much to his dismay, the struggling Asian American actor is only ever cast in parts like Generic Asian Man Number One or Background Oriental Male.\n" +
                            "\n" +
                            "He is, however, the main protagonist of the satirical novel Interior Chinatown, which last month won one of the most prestigious literary prizes in the US, the National Book Award for fiction.\n" +
                            "\n" +
                            "Its author, Charles Yu, says he wrote the book to \"create a space where background characters get to have a story\".\n" +
                            "\n" +
                            "Even more than that, he hopes the novel will shed more light on the ongoing debate about representation and Asian American stereotypes, and create a conversation about escaping the roles we are forced into.\n" +
                            "\n" +
                            "In the words of Willis Wu: \"To be yellow in America... a special guest star, forever the guest.\"\n" +
                            "\n" +
                            "Not all black and white\n" +
                            "A former lawyer, Yu first shot to fame in 2010 with his novel How to Live Safely in a Science Fictional Universe.\n" +
                            "\n" +
                            "He then went on to work as a writer for the award-winning HBO television series Westworld, before returning to literature.\n" +
                            "\n" +
                            "\"I struggled for years trying to get [the book] off the ground,\" the 44-year-old tells the BBC in a recent Zoom interview from his southern California home.\n" +
                            "\n" +
                            "But a major \"catalyst\" that got him going, he says, was the election of President Donald Trump in 2016.",
                    "8 june",
                    false,
                    false,
                    "https://ichef.bbci.co.uk/news/976/cpsprodpb/10DC3/production/_115795096_gettyimages-515119974.jpg",
                    "#f94144"
                ),

                )

            articlesDao.addArticles(listOfEntertainmentArticles)


        }

    }

    companion object {

        private var Instance: ArticlesDataBase? = null
        private val LOCK = Any()


        operator fun invoke(context: Context) = Instance
            ?: synchronized(LOCK) {

                Instance
                    ?: buildDatabase(
                        context
                    )
                        .also { Instance = it }


            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            ArticlesDataBase::class.java, "Articles.db"
        ).fallbackToDestructiveMigration().addCallback(ArticleDatabaseCallBack())
            .build()

    }


}