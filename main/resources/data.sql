INSERT INTO company(company_name, country, region) VALUES ('원티드랩', '한국', '서울');
INSERT INTO company(company_name, country, region) VALUES ('네이버', '한국', '판교');
INSERT INTO company(company_name, country, region) VALUES ('원티드코리아', '한국', '부산');
INSERT INTO company(company_name, country, region) VALUES ('카카오', '한국', '판교');

INSERT INTO employment(company_id, employment_position, employment_carrot, employment_content, used_technique)
VALUES (1, '백엔드 주니어 개발자', 1500000, '원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..', 'Python');
INSERT INTO employment(company_id, employment_position, employment_carrot, employment_content, used_technique)
VALUES (2, 'Django 백엔드 개발자', 1000000, '네이버에서 백엔드 개발자를 채용합니다. 자격요건은..', 'Django');