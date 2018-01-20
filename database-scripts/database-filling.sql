-- Filling table internet_provider.plan.

INSERT INTO internet_provider.plan
    (plan.name,
    plan.download_speed,
    plan.upload_speed,
    plan.price,
    plan.description)
VALUES
    ('Анлим 25',
    '25',
    '10',
    '10.00',
    'Этот тариф подойдёт для любителей сёрфить интернет, социльных сетей, а также прослушивания музыки онлайн.'),

    ('Анлим 50',
    '50',
    '25',
    '15.00',
    'Этот тариф отлично подойдёт для любителей провести вечер за просмотром кино онлайн или просто любимых youtube-каналов. С этим тарифом вам не придётся подолгу ждать загрузки любимого видео!'),

    ('Анлим 100',
    '100',
    '50',
    '20.00',
    'Этот тариф предназначен для любителей больших скоростей, а так же для заядлых геймеров. С этим тарифом вы не только сможете чувствовать себя очень комфортно в онлайн-играх, но также сможете устроить онлайн-трайнсляцию, чтобы поделиться своими достижениями с другими игроками!');

-- Filling table internet_provider.user.

INSERT INTO internet_provider.user
    (user.email,
    user.password,
    user.first_name,
    user.middle_name,
    user.last_name,
    user.contract,
    user.balance,
    user.is_blocked,
    user.city,
    user.street,
    user.building,
    user.apartments,
    user.plan_id)
VALUES
    -- password: password1
    ('pavel.yudzitski@gmail.com',
    'e38ad214943daad1d64c102faec29de4afe9da3d',
    'Павел',
    'Иванович',
    'Юдицкий',
    '100000001',
    '40.00',
    '0',
    'Минск',
    'Яна Чечота',
    '8',
    '38',
    (SELECT plan.id FROM internet_provider.plan WHERE plan.name = 'Анлим 100')),

    ('ivan.ivanov@mail.ru',
    '24b3eb0aa0d2c1264d92ab2be4e0cac5300ed883',
    'Иван',
    'Иванович',
    'Иванов',
    '100000002',
    '23.73',
    '0',
    'Слуцк',
    'Ленина',
    '1',
    '1',
    (SELECT plan.id FROM internet_provider.plan WHERE plan.name = 'Анлим 25')),

    ('anna.kirpich@mail.com',
    '7cd8e453cc73e870bda32e76850e3f3fa89d339f',
    'Анна',
    'Олеговна',
    'Кирпич',
    '100000405',
    '17.17',
    '0',
    'Борисов',
    'Чапаева',
    '15',
    '3',
    (SELECT plan.id FROM internet_provider.plan WHERE plan.name = 'Анлим 50')),

    ('petia88@yandex.ru',
    '2a6aa83cc72c00cb74c0987cdf12c54e32a09a47',
    'Петр',
    'Александрович',
    'Жуков',
    '212542255',
    '-0.89',
    '1',
    'Мядель',
    'Ленина',
    '33',
    '3',
    (SELECT plan.id FROM internet_provider.plan WHERE plan.name = 'Анлим 100')),

    ('alex2015@mail.ru',
    '8814506a1940c692a79751295b0b6b46074bb901',
    'Алексей',
    'Сергеевич',
    'Новик',
    '100011272',
    '25.99',
    '1',
    'Минск',
    'Независимости',
    '78',
    '113',
    (SELECT plan.id FROM internet_provider.plan WHERE plan.name = 'Анлим 50')),

    ('pustozvonoff@gmail.com',
    'e0ffc61c2feece6c2317d8527a60bc1bb45d5ca8',
    'Аркадий',
    'Михайлович',
    'Пустозвонов',
    '129848901',
    '5.01',
    '0',
    'Мозырь',
    'Снежная',
    '101',
    '1',
    (SELECT plan.id FROM internet_provider.plan WHERE plan.name = 'Анлим 100'));

-- Filling table internet_provider.customer.

INSERT INTO internet_provider.customer
    (customer.email,
    customer.password,
    customer.first_name,
    customer.middle_name,
    customer.last_name,
    customer.city,
    customer.street,
    customer.building,
    customer.apartments,
    customer.plan_id)
VALUES
    ('myemail@email.org',
    'ace7c38be2b4c405a887d24d2867c6b599364e63',
    'Евпатий',
    'Филимонович',
    'Дрожинский',
    'Минск',
    'Победы',
    '19',
    '45',
    (SELECT plan.id FROM internet_provider.plan WHERE plan.name = 'Анлим 25')),

    ('semen.paramonov@gmail.com',
    '8ab710950fb70e01c4d6d28c908caf4f19a608e3',
    'Семен',
    'Евгениевич',
    'Парамонов',
    'Борисов',
    'Пушкина',
    '6',
    '6',
    (SELECT plan.id FROM internet_provider.plan WHERE plan.name = 'Анлим 50')),

    ('evgeshaKitty@mail.ru',
    'bb38bb3caf17d0162d1c7e4e36c17fa4f01d8429',
    'Евгения',
    'Павловна',
    'Задубай',
    'Витебск',
    'Солнечная',
    '43',
    '120',
    (SELECT plan.id FROM internet_provider.plan WHERE plan.name = 'Анлим 100'));

-- Filling table internet_provider.admin.

INSERT INTO internet_provider.admin
    (admin.personnel_number,
    admin.email,
    admin.password,
    admin.first_name,
    admin.middle_name,
    admin.last_name)
VALUES
    -- password: admin
    ('10000',
    'admin@gigabit.by',
    'd033e22ae348aeb5660fc2140aec35850c4da997',
    'Константин',
    'Валерьевич',
    'Дюжев');

-- Filling table internet_provider.traffic.

INSERT INTO internet_provider.traffic
    (traffic.downloaded,
    traffic.uploaded,
    traffic.date,
    traffic.user_id)
VALUES
    ('2485.12',
    '2085.8',
    '2017-12-01',
    (SELECT user.id from internet_provider.user where user.email = 'pavel.yudzitski@gmail.com')),

    ('2305.78',
    '1420.35',
    '2017-12-01',
    (SELECT user.id from internet_provider.user where user.email = 'ivan.ivanov@mail.ru')),

    ('1703.55',
    '2583.65',
    '2017-12-01',
    (SELECT user.id from internet_provider.user where user.email = 'anna.kirpich@mail.com')),

    ('516.76',
    '1871.1',
    '2017-12-01',
    (SELECT user.id from internet_provider.user where user.email = 'petia88@yandex.ru')),

    ('856.82',
    '1274.04',
    '2017-12-01',
    (SELECT user.id from internet_provider.user where user.email = 'alex2015@mail.ru')),

    ('707.07',
    '2277.95',
    '2017-12-01',
    (SELECT user.id from internet_provider.user where user.email = 'pustozvonoff@gmail.com')),

    ('2963.89',
    '2081.66',
    '2017-12-02',
    (SELECT user.id from internet_provider.user where user.email = 'pavel.yudzitski@gmail.com')),

    ('1910.52',
    '1683.94',
    '2017-12-02',
    (SELECT user.id from internet_provider.user where user.email = 'ivan.ivanov@mail.ru')),

    ('1884.53',
    '2263.99',
    '2017-12-02',
    (SELECT user.id from internet_provider.user where user.email = 'anna.kirpich@mail.com')),

    ('700.42',
    '2003.41',
    '2017-12-02',
    (SELECT user.id from internet_provider.user where user.email = 'petia88@yandex.ru')),

    ('1382.8',
    '2851.87',
    '2017-12-02',
    (SELECT user.id from internet_provider.user where user.email = 'alex2015@mail.ru')),

    ('1787.47',
    '950.18',
    '2017-12-02',
    (SELECT user.id from internet_provider.user where user.email = 'pustozvonoff@gmail.com')),

    ('2369.92',
    '2604.25',
    '2017-12-03',
    (SELECT user.id from internet_provider.user where user.email = 'pavel.yudzitski@gmail.com')),

    ('2177.12',
    '2684.01',
    '2017-12-03',
    (SELECT user.id from internet_provider.user where user.email = 'ivan.ivanov@mail.ru')),

    ('2428.28',
    '742.82',
    '2017-12-03',
    (SELECT user.id from internet_provider.user where user.email = 'anna.kirpich@mail.com')),

    ('2693.93',
    '1560.7',
    '2017-12-03',
    (SELECT user.id from internet_provider.user where user.email = 'petia88@yandex.ru')),

    ('2529.68',
    '2178.03',
    '2017-12-03',
    (SELECT user.id from internet_provider.user where user.email = 'alex2015@mail.ru')),

    ('2126.69',
    '2843.54',
    '2017-12-03',
    (SELECT user.id from internet_provider.user where user.email = 'pustozvonoff@gmail.com')),

    ('2667.04',
    '967.55',
    '2017-12-03',
    (SELECT user.id from internet_provider.user where user.email = 'pavel.yudzitski@gmail.com')),

    ('2133.83',
    '1675.98',
    '2017-12-03',
    (SELECT user.id from internet_provider.user where user.email = 'pavel.yudzitski@gmail.com')),

    ('2529.97',
    '1031.65',
    '2017-12-03',
    (SELECT user.id from internet_provider.user where user.email = 'pavel.yudzitski@gmail.com'));

-- Filling table internet_provider.payment.

INSERT INTO internet_provider.payment
    (payment.sum,
    payment.date,
    payment.user_id)
VALUES
    ('25.00',
    '2017-12-01',
    (SELECT user.id from internet_provider.user where user.email = 'pavel.yudzitski@gmail.com')),

    ('10.00',
    '2017-11-15',
    (SELECT user.id from internet_provider.user where user.email = 'ivan.ivanov@mail.ru')),

    ('15.00',
    '2017-11-28',
    (SELECT user.id from internet_provider.user where user.email = 'anna.kirpich@mail.com')),

    ('10.00',
    '2017-12-03',
    (SELECT user.id from internet_provider.user where user.email = 'petia88@yandex.ru')),

    ('30.00',
    '2017-11-25',
    (SELECT user.id from internet_provider.user where user.email = 'alex2015@mail.ru')),

    ('5.00',
    '2017-12-02',
    (SELECT user.id from internet_provider.user where user.email = 'pustozvonoff@gmail.com')),

    ('15.00',
    '2017-11-17',
    (SELECT user.id from internet_provider.user where user.email = 'pavel.yudzitski@gmail.com')),

    ('20.00',
    '2017-11-01',
    (SELECT user.id from internet_provider.user where user.email = 'pavel.yudzitski@gmail.com')),

    ('13.00',
    '2017-12-01',
    (SELECT user.id from internet_provider.user where user.email = 'pavel.yudzitski@gmail.com'));
