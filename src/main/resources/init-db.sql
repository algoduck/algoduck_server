set
autocommit = 0;

DELETE FROM versions;
DELETE FROM languages;
DELETE FROM testcases;
DELETE FROM problems;

UPDATE problems_seq SET next_val = 0;

INSERT INTO problems (problem_id, problem_number, title, description, input_description, output_description,
                      time_limitation, memory_limitation, difficulty, created_at)
VALUES (1000,
        1000,
        '알고오리',
        '자연수 N을 입력받아서 “알고오리”를 N번 출력한다.',
        '첫번째 줄에 자연수 N이 주어진다. (1 ≤ N ≤ 1,000)',
        'N개의 줄에 “알고오리”를 출력한다. (“”는 출력하지 않는다.)',
        2,
        128,
        0,
        now()),
       (1001,
        1001,
        '두 수 더하기',
        '두 자연수 A와 B를 입력받아 그 합을 출력하라.',
        '첫 번째 줄에 두 자연수 A, B가 주어진다. (1 ≤ A, B ≤ 2,000,000,000)',
        '두 자연수 A와 B의 합을 출력한다.',
        2,
        128,
        0,
        now()),
       (1002,
        1002,
        '군집 내 가장 먼 두 도시 사이 거리',
        'Algoduck 국가에는 N개의 도시가 존재하고, 이 도시들은 M개의 서로 다른 도로로 연결되어 있다. 각 도로는 반드시 서로 다른 두 도시를 연결하며 양방향으로 이동 가능하다. 하지만 N개의 도시 중 임의의 두 도시 사이를 도로를 통해 반드시 이동할 수 있다는 보장은 없다. 서로 이동할 수 있는 도시들끼리는 군집(Cluster)를 이루고 있다.

어떤 군집의 임의의 두 도시 사이의 경로는 반드시 하나 존재한다. 이 때 각 군집에서 가장 먼 도시 사이의 거리를 오름차순으로 출력하라. 만약 군집 내의 도시의 개수가 1개일 경우 0을 출력한다.',
        '- 첫번째 줄에 두 자연수 N, M이 주어진다. (1 ≤ N ≤ 100,000, 0 ≤ M < N)
- 두 번째 줄부터 M개의 줄에는 세 자연수 u, v, w가 주어진다. (1 ≤ u, v ≤ N, 1 ≤ w ≤ 100,000) 이는 u번째 도시와 v번째 도시가 길이가 w인 양방향 도로로 연결되었다는 뜻이다.',
        '가장 먼 두 도시 사이의 거리가 가까운 순으로 k개의 군집 C1, C2, …, Ck가 존재하면 그 거리를 d1, d2, …, dk로 오름차순으로 k개의 줄에 출력한다.',
        2,
        256,
        0,
        now()),
       (1003,
        1003,
        '감염 전파 시간 계산',
        'Algoduck 국가의 수도 콰코어(Quackcore) 시는 계획도시로 N x M 크기의 격자모양으로 건설되었다. 각 격자마다 한 개씩 건물이 존재한다.
콰코어의 건물 중 음이 아닌 정수개의 건물이 DI(Duck Influenza) 바이러스에 감염되었으며 감염은 1시간마다 한 칸씩 상하좌우로 인접한 건물로 전염된다. 콰코어 시의 시장은 DI 바이러스가 퍼지는 것을 막기 위해 도시 내 건물 중 0개 이상의 건물을 철거하고 대신 방호벽을 세웠다. DI 바이러스는 방호벽이 있는 격자로는 전염될 수 없다.
방호벽을 제외한 모든 건물이 DI 바이러스에 감염되는 최소 시간을 구하여라. DI 바이러스 최초 감염은 동시에 발생했으며 방호벽을 제외한 건물 중 하나라도 DI 바이러스에 감염시킬 수 없다면 -1을 출력한다.',
        '• 첫번째 줄에 도시의 크기 N, M이 주어진다. (1 ≤ N ≤ 1,000, 1 ≤ M ≤ 1,000)
• 두 번째 줄부터 N개의 줄에는 DI 바이러스 감염이 시작되었을때 도시의 상황이 주어진다. 0은 감염이 퍼지지 않은 건물, 1은 감염이 시작된 건물, -1은 방호벽을 의미한다.',
        '방호벽을 제외한 모든 건물을 DI 바이러스에 감염시키기 위한 최소 시간을 출력한다. 만약 하나의 건물이라도 바이러스에 감염시킬 수 없을 경우 -1을 출력한다.',
        2,
        192,
        0,
        now()),
       (1004,
        1004,
        '제초하기',
        'Algoduck 국가의 수도 콰코어(Quackcore)의 중앙에는 직사각형 모양의 광장 덕고라(Duckgora)가 존재한다. 덕고라는 1 * 1 크기의 정사각형 격자가 세로로 N개, 가로로 M개, 총 NM개의 정사각형 격자로 이루어져있다. 각각의 정사각형 격자는 좌표로 나타낼 수 있는데 북서쪽 격자는 (1, 1), 남동쪽 격자는 (N, M)으로 나타낼 수 있다.
   Koreii가 덕고라를 만들 당시 NM개의 정사각형 격자 중 임의의 격자에 풀을 심었다. Koreii는 여기저기 난잡하게 심어진 풀이 꼴 보기 싫어져서 크기가 가장 큰 풀밭의 풀들을 제외한 모든 풀을 제거하기로 결정했다. 풀밭이란 풀이 심어진 격자들이 직사각형을 이룬 집합이다.
   Koreii가 남겨둘 풀밭의 크기와 풀밭의 북서쪽 격자 좌표, 남동쪽 격자 좌표를 찾아보자.',
        '• 첫번째 줄에 덕고라의 세로 크기 N, 가로 크기 M이 주어진다. (1 ≤ N ≤ 444, 1 ≤ M ≤ 444)
• 다음 N개의 줄에는 덕고라의 각 줄의 격자 상태를 나타내는 정수가 공백으로 구분되어 주어진다.
    ◦ 0 : 풀이 심어지지 않은 격자
    ◦ 1 : 풀이 심어진 격자
• 적어도 하나의 격자에는 풀이 심어져 있음이 보장된다.',
        '• 첫 번째 줄에는 남겨둘 풀밭의 크기를 출력한다. 풀밭의 크기란 풀밭에 포함된 풀이 심어진 격자의 개수이다.
   • 두 번째 줄에는 남겨둘 풀밭의 북서쪽 격자 좌표 (y1, x1)과 남동쪽 격자 좌표 (y2, x2)를 출력한다. (1 ≤ y1 ≤ y2 ≤ N, 1 ≤ x1 ≤ x2 ≤ M) 크기가 가장 큰 풀밭이 여러개일 경우 y1이 가장 작은 것, y1이 가장 작은 것이 여러개일 경우 x1이 가장 작은 것, x1이 가장 작은 것이 여러개일 경우, y2가 가장 작은 것, y2가 가장 작은 것이 여러개일 경우 x2가 가장 작은 것을 출력한다.',
        2,
        128,
        0,
        now()),
    (1005,
     1005,
     '독립',
     'Algoduck 국가의 숲에는 오리가 살고 있는 나무가 많다. 그 중 어떤 나무에는 어미 오리와 새끼 오리들이 살고 있다. 어미 오리는 새끼 오리들을 독립시키기 위해 나는 법을 가르쳐야 한다. 새끼 오리들을 강하게 기르기 위해 어미 오리는 새끼 오리들을 나무의 특정 높이에서 밀어서 땅으로 떨어뜨린다.
새끼 오리들은 매우 가볍기 때문에 나무의 어떤 높이에서 땅에 떨어지더라도 전혀 다치지 않는다. 새끼 오리들 또한 이를 잘 알고있기에 어지간한 높이에서는 떨어지더라도 날개짓조차 하지 않는다. 하지만 특정 높이 이상의 높이에서 떨어질 경우 본능적으로 두려움을 느끼고 날개짓을 하며 날아가버린다. 날아가버린 새끼 오리는 그 순간 독립하여 다시는 돌아오지 않는다.
새끼 오리의 수 N, 나무의 높이 H가 주어질 때 새끼 오리들이 떨어질 때 두려움을 느끼고 날개짓을 하는 최소 높이를 구하기 위해 새끼 오리를 떨어뜨리는 시행을 최소 몇 번 해야하는지를 구하시오. 나무의 높이 H는 m 단위이며 오리가 떨어지는 높이 또한 1m 단위로 정해져 있다. 모든 새끼 오리가 두려움을 느끼는 높이는 동일하며 1m에서도 두려움을 느낄 수도 있고, Hm에서조차도 두려움을 느끼지 않을 수도 있다. (이 경우에는 새끼 오리들은 영원히 독립하지 못할 것이다.)'
       ,
     '• 새끼 오리의 수 N, 나무의 높이 H가 주어진다. (1 ≤ N ≤ 100, 1 ≤ H ≤ 1,000)',
     '• 문제에서 요구하는 최소 시행 횟수를 출력한다.',
     2,
     128,
     0,
     now());

UPDATE problems_seq
SET next_val = 1006;

INSERT INTO languages (language_id, name, extension, created_at) VALUES (1001, "Java", "java", now());
INSERT INTO versions (version_id, language_id, version_name, created_at) VALUES (1001, 1001, "Java 8", now());
INSERT INTO versions (version_id, language_id, version_name, created_at) VALUES (1002, 1001, "Java 11", now());
INSERT INTO versions (version_id, language_id, version_name, created_at) VALUES (1003, 1001, "Java 15", now());
INSERT INTO versions (version_id, language_id, version_name, created_at) VALUES (1004, 1001, "Java 17", now());

UPDATE languages_seq SET next_val = 1002;
UPDATE versions_seq SET next_val = 1005;

UPDATE testcases_seq SET next_val = 0;

INSERT INTO testcases (testcase_id, problem_id, testcase_input_name, testcase_input_url, testcase_input_data,
                       testcase_output_name, testcase_output_url, testcase_output_data, is_public, created_at)
VALUES (1, 1000, "input01", "", null, "output01", "", null, false, now()),
       (2, 1000, "input02", "", null, "output02", "", null, false, now()),
       (3, 1000, "input03", "", null, "output03", "", null, false, now()),
       (4, 1000, "input04", "", null, "output04", "", null, false, now()),
       (5, 1000, "input05", "", "1", "output05", "", "알고오리", true, now());
INSERT INTO testcases (testcase_id, problem_id, testcase_input_name, testcase_input_url, testcase_input_data,
                       testcase_output_name, testcase_output_url, testcase_output_data, is_public, created_at)
VALUES (6, 1001, "input01", "", null, "output01", "", null, false, now()),
       (7, 1001, "input02", "", null, "output02", "", null, false, now()),
       (8, 1001, "input03", "", null, "output03", "", null, false, now()),
       (9, 1001, "input04", "", null, "output04", "", null, false, now()),
       (10, 1001, "input05", "", null, "output05", "", null, false, now()),
       (11, 1001, "input06", "", null, "output06", "", null, false, now()),
       (12, 1001, "input07", "", null, "output07", "", null, false, now()),
       (13, 1001, "input08", "", null, "output08", "", null, false, now()),
       (14, 1001, "input09", "", null, "output09", "", null, false, now()),
       (15, 1001, "input10", "", null, "output10", "", null, false, now()),
       (16, 1001, "input11", "", null, "output11", "", null, false, now()),
       (17, 1001, "input12", "", "1 1", "output12", "", "2", true, now()),
       (18, 1001, "input13", "", "40 26", "output13", "", "66", true, now()),
       (19, 1001, "input14", "", null, "output14", "", null, false, now()),
       (20, 1001, "input15", "", null, "output15", "", null, false, now());
INSERT INTO testcases (testcase_id, problem_id, testcase_input_name, testcase_input_url, testcase_input_data,
                       testcase_output_name, testcase_output_url, testcase_output_data, is_public, created_at)
VALUES (21, 1002, "input01", "", null, "output01", "", null, false, now()),
       (22, 1002, "input02", "", null, "output02", "", null, false, now()),
       (23, 1002, "input03", "", null, "output03", "", null, false, now()),
       (24, 1002, "input04", "", null, "output04", "", null, false, now()),
       (25, 1002, "input05", "", null, "output05", "", null, false, now()),
       (26, 1002, "input06", "", null, "output06", "", null, false, now()),
       ( 27, 1002, "input07", ""
       , "15 11\n9 3 12\n14 10 12\n14 6 12\n3 8 7\n15 2 6\n11 12 5\n3 11 15\n12 6 6\n5 15 9\n3 1 10\n12 7 14"
       , "output07", "", "0\n0\n15\n62"
       , true, now()),
       (28, 1002, "input08", "", null, "output08", "", null, false, now()),
       ( 29, 1002, "input09", "", "5 3\n1 3 102\n2 4 258\n3 5 291"
       , "output09", "", "258\n393", true, now()),
       (30, 1002, "input10", "", null, "output10", "", null, false, now()),
       (31, 1002, "input11", "", null, "output11", "", null, false, now());
INSERT INTO testcases (testcase_id, problem_id, testcase_input_name, testcase_input_url, testcase_input_data,
                       testcase_output_name, testcase_output_url, testcase_output_data, is_public, created_at)
VALUES (32, 1003, "input01", "", null, "output01", "", null, false, now()),
       (33, 1003, "input02", "", null, "output02", "", null, false, now()),
       (34, 1003, "input03", "", null, "output03", "", null, false, now()),
       (35, 1003, "input04", "", null, "output04", "", null, false, now()),
       (36, 1003, "input05", "", null, "output05", "", null, false, now()),
       (37, 1003, "input06", "", null, "output06", "", null, false, now()),
       (38, 1003, "input07", "", "14 7\n0 0 0 1 0 0 0\n0 0 0 0 0 -1 0\n0 0 0 0 0 1 0\n0 0 0 0 1 0 0\n1 1 0 0 0 1 1\n0 0 0 0 0 0 0\n0 0 0 0 0 0 0\n0 0 0 -1 0 0 0\n0 0 -1 0 -1 1 1\n0 0 0 -1 0 0 1\n0 0 0 0 0 1 0\n0 0 0 -1 0 0 1\n-1 0 -1 0 1 1 1\n-1 0 0 1 0 0 0"
       , "output07", "", "-1", true, now()),
       (39, 1003, "input08", "", null, "output08", "", null, false, now()),
       (40, 1003, "input09", "", null, "output09", "", null, false, now()),
       (41, 1003, "input10", "", null, "output10", "", null, false, now()),
       (43, 1003, "input12", "", null, "output12", "", null, false, now()),
       ( 44, 1003, "input13", ""
       , "9 6\n0 0 0 1 0 0 \n0 0 0 0 0 0 \n0 0 1 0 1 0 \n0 1 0 0 0 0 \n0 -1 0 0 0 0 \n0 0 0 0 0 0 \n-1 -1 0 -1 1 0 \n0 0 1 0 1 0 \n0 1 1 0 1 1"
       , "output13", "", "3", true, now()),
       ( 45, 1003, "input14", ""
       , "9 7\n1 0 0 0 0 0 0\n-1 -1 -1 -1 -1 -1 0\n0 0 0 0 0 0 0\n0 -1 -1 -1 -1 -1 -1\n0 0 0 0 0 0 0\n-1 -1 -1 -1 -1 -1 0\n0 0 0 0 0 0 0\n0 -1 -1 -1 -1 -1 -1\n0 0 0 0 0 0 0"
       , "output14", "", "38", true, now()),
       (46, 1003, "input15", "", null, "output15", "", null, false, now()),
       (47, 1003, "input16", "", null, "output16", "", null, false, now()),
       (48, 1003, "input17", "", null, "output17", "", null, false, now()),
       (49, 1003, "input18", "", null, "output18", "", null, false, now());

INSERT INTO testcases (testcase_id, problem_id, testcase_input_name, testcase_input_url, testcase_input_data,
                       testcase_output_name, testcase_output_url, testcase_output_data, is_public, created_at)
VALUES (50, 1003, "input19", "", null, "output19", "", null, false, now()),
       (51, 1004, "input01", "", null, "output01", "", null, false, now()),
       (52, 1004, "input02", "", null, "output02", "", null, false, now()),
       (53, 1004, "input03", "", null, "output03", "", null, false, now()),
       (54, 1004, "input04", "", null, "output04", "", null, false, now()),
       (55, 1004, "input05", "", null, "output05", "", null, false, now()),
       (56, 1004, "input06", "", null, "output06", "", null, false, now()),
       (57, 1004, "input07", "", null, "output07", "", null, false, now()),
       (58, 1004, "input08", "", null, "output08", "", null, false, now()),
       (59, 1004, "input09", "", null, "output09", "", null, false, now()),
       (60, 1004, "input10", "", null, "output10", "", null, false, now()),
       (61, 1004, "input11", "", null, "output11", "", null, false, now()),
       (62, 1004, "input12", "", null, "output12", "", null, false, now()),
       ( 63, 1004, "input13", ""
       , "6 9\n0 0 0 0 0 0 1 1 1\n1 1 1 0 0 0 1 1 1\n1 1 1 1 1 0 1 1 1\n0 1 1 1 1 1 1 1 1\n0 0 0 0 0 0 1 1 0\n1 1 1 0 0 0 1 1 0"
       , "output13", "", "12\n1 7 4 9", true, now()),
       ( 64, 1004, "input14", "", "3 3\n1 1 1\n1 1 0\n0 1 1"
       , "output14", "", "4\n1 1 2 2"
       , true, now()),
       (65, 1004, "input15", "", null, "output15", "", null, false, now());

INSERT INTO testcases (testcase_id, problem_id, testcase_input_name, testcase_input_url, testcase_input_data,
                       testcase_output_name, testcase_output_url, testcase_output_data, is_public, created_at)
VALUES (66, 1005, "input01", "", null, "output01", "", null, false, now()),
       (67, 1005, "input02", "", null, "output02", "", null, false, now()),
       (68, 1005, "input03", "", null, "output03", "", null, false, now()),
       (69, 1005, "input04", "", null, "output04", "", null, false, now()),
       (70, 1005, "input05", "", null, "output05", "", null, false, now()),
       (71, 1005, "input06", "", null, "output06", "", null, false, now()),
       (72, 1005, "input07", "", null, "output07", "", null, false, now()),
       (73, 1005, "input08", "", null, "output08", "", null, false, now()),
       (74, 1005, "input09", "", null, "output09", "", null, false, now()),
       (75, 1005, "input10", "", null, "output10", "", null, false, now()),
       (76, 1005, "input11", "", null, "output11", "", null, false, now()),
       (77, 1005, "input12", "", null, "output12", "", null, false, now()),
       (78, 1005, "input13", "", null, "output13", "", null, false, now()),
       (79, 1005, "input14", "", null, "output14", "", null, false, now()),
       (80, 1005, "input15", "", null, "output15", "", null, false, now()),
       (81, 1005, "input16", "", null, "output16", "", null, false, now()),
       (82, 1005, "input17", "", null, "output17", "", null, false, now()),
       (83, 1005, "input18", "", null, "output18", "", null, false, now()),
       (84, 1005, "input19", "", null, "output19", "", null, false, now()),
       (85, 1005, "input20", "", null, "output20", "", null, false, now()),
       (86, 1005, "input21", "", null, "output21", "", null, false, now()),
       (87, 1005, "input22", "", null, "output22", "", null, false, now()),
       (88, 1005, "input23", "", null, "output23", "", null, false, now()),
       (89, 1005, "input24", "", null, "output24", "", null, false, now()),
       (90, 1005, "input25", "", null, "output25", "", null, false, now()),
       (91, 1005, "input26", "", null, "output26", "", null, false, now()),
       (92, 1005, "input27", "", null, "output27", "", null, false, now()),
       (93, 1005, "input28", "", null, "output28", "", null, false, now()),
       (94, 1005, "input29", "", null, "output29", "", null, false, now()),
       (95, 1005, "input30", "", null, "output30", "", null, false, now()),
       (96, 1005, "input31", "", "2 10", "output31", "", "4", true, now()),
       (97, 1005, "input32", "", null, "output32", "", null, false, now()),
       (98, 1005, "input33", "", null, "output33", "", null, false, now()),
       (99, 1005, "input34", "", null, "output34", "", null, false, now()),
       (100, 1005, "input35", "", "49 11", "output35", "", "4", true, now()),
       (101, 1005, "input36", "", null, "output36", "", null, false, now()),
       (102, 1005, "input37", "", "1 100", "output37", "", "100", true, now()),
       (103, 1005, "input38", "", null, "output38", "", null, false, now());

UPDATE testcases
SET
    testcase_input_url = CONCAT('https://algoduck-testcase-bucket-ec2-810270402.s3.ap-northeast-2.amazonaws.com/prob_', LPAD(problem_id, 5, '0'), '/', testcase_input_name),
    testcase_output_url = CONCAT('https://algoduck-testcase-bucket-ec2-810270402.s3.ap-northeast-2.amazonaws.com/prob_', LPAD(problem_id, 5, '0'), '/', testcase_output_name);

UPDATE testcases_seq SET next_val = 104;

START TRANSACTION;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TX7U8lkduef2',
  'T3Gqpx',
  'TX7U8lkduef2@gmail.com',
  '$2a$04$x7LoW4GGCGQYSZoziKKazOWZZ1AQwGWcaARFCb9vAjYQM6Vo5uRmm',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'Tbl6HfYyR',
  'T9jAhz',
  'Tbl6HfYyR@naver.com',
  '$2a$04$F8rWLzR7TwayFX0LfANsZupYNCL.1x59fyGzSoCVwdVV5HlvwvKRC',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T1bKUQMyyJ',
  'TwGM8Y나bq',
  'T1bKUQMyyJ@yahoo.com',
  '$2a$04$QCL.rkS.WQhF8z17ygD3AehyFfECH98qKlZZkDSj6MoS/FKMdXEYq',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'Tra4IGCkLPMAC1M',
  'TR99나8K8cJ',
  'Tra4IGCkLPMAC1M@gmail.com',
  '$2a$04$kBy6oe/n1nV/LhZi8XYU1utWxEZD4TkaWGLIRB9qGsmcS22mb4DiO',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T2nOVhv',
  'TPpq',
  'T2nOVhv@icloud.com',
  '$2a$04$5pk78jDT/vlQc8BzvKya6uoSA7AmzUng/iOWtw1DANrGhP/dG.5Sm',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TldvL7I6q',
  'T6사WPb2Y마',
  'TldvL7I6q@icloud.com',
  '$2a$04$Gw5eu19chHn59yezP/HJde8jzCUwueZcrj7QK2ENqwSk0TfXHAelq',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TeYOrN71js',
  'Tbm9O',
  'TeYOrN71js@yahoo.com',
  '$2a$04$QxOeO4V.h7CUb7IyBucfh.w2A91tRSZtBbuIqpdTKh7BPPXcE0BS2',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TjS7Zk7kMyJS8UQ',
  'TC하or라pY',
  'TjS7Zk7kMyJS8UQ@icloud.com',
  '$2a$04$lqf2iJtlGK90Vr.q6X0y8.SDC/wSi0KKG.3h5WQ3Uz.ia0TgjjQS2',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TXfCPIn1eEw3ZWP',
  'TOq가0MT3h',
  'TXfCPIn1eEw3ZWP@yahoo.com',
  '$2a$04$hU4o/oJ4.7n8fpDXsLNAwOAOI39BzyogwayYj2eeRfO37OZIZQWMG',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TxqpTH1K',
  'T681사8',
  'TxqpTH1K@gmail.com',
  '$2a$04$614BC16WQEn/OZLAAUg/c.iUKnP/5YfUcdoyydIKXAQ3y1KdRTAXm',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T4bC6npn9VxYZVL',
  'T7Oy',
  'T4bC6npn9VxYZVL@hanmail.net',
  '$2a$04$U12lMrz8txyfzCfkcD6eyu6/OpjtTF7U9wZRbq7czGLaClmzps97e',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'Tg7GCWqs',
  'TK6A마y',
  'Tg7GCWqs@gmail.com',
  '$2a$04$EdcvPW3f8rJOQnk.j9zE5O8U0esY.OQjIrM4d47B6LyNiyiO.hRrK',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TlufR7lAFQ7sj2',
  'TcM1',
  'TlufR7lAFQ7sj2@gmail.com',
  '$2a$04$wZp5XJ2MyejxwMCvvGqpwe9V57ZUn0hcCcWPAWtIcVEJFvvMmBv0q',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'Tw7iPqS9LMlhtMva',
  'Tq하타OLe1',
  'Tw7iPqS9LMlhtMva@naver.com',
  '$2a$04$voneyHt5HvHJaoRpeFL5.u4OyTY4e3zY70tV5bT6XbZ9wqWyYOqwW',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T73HB3tm7znxxaJ1dw86',
  'TPde1',
  'T73HB3tm7znxxaJ1dw86@yahoo.com',
  '$2a$04$wrAsZ8l1lWhLrT9SAbRxHOoatQm52SQSAzmWZ4ahc37R7M9N6ht76',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'Tq0WApKwubGfZu1P',
  'T2ws8G나자',
  'Tq0WApKwubGfZu1P@yahoo.com',
  '$2a$04$knoDW8FeyO8lOpsAD/wm1uyaDgRwmMZE8o1kHweGISS8GiNN38bLi',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TSfmHabfNrok551Ji',
  'T3z5다8바tA',
  'TSfmHabfNrok551Ji@hanmail.net',
  '$2a$04$5NI0oYhzfASsFaM5dQDu6endzIKun6aQ7pQMBKg1s.ZGq0EiK/0Au',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'Tc2R4nIlVW',
  'T바카U',
  'Tc2R4nIlVW@yahoo.com',
  '$2a$04$/BAbj9usrab6d/meUUNoWurc4y4.OqNtXI289zw5bgA7oD27hPVW2',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'Tf9ChXtv4YLGtJ',
  'Tq아No7아Tm',
  'Tf9ChXtv4YLGtJ@naver.com',
  '$2a$04$rA5orNemv2O2unFNF83yROrMDFNwqifvrtWs/Q38DfLC6cHFLGkr.',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TprKx1Be2g7',
  'TmIa사g',
  'TprKx1Be2g7@naver.com',
  '$2a$04$CrR80IBoAzRCOYdPaePVaOT9HZBv3bd2SMNsnJ5YuCoQPOEl7byq.',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'Tq3xXFM8f73Ef',
  'TT하70',
  'Tq3xXFM8f73Ef@icloud.com',
  '$2a$04$VYjtbsxC7M098fmh2drNNeNcmL31bjqzN0m6F9iBj5FBVS8ZxMXyC',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TooR93D98i91L',
  'Tift아',
  'TooR93D98i91L@yahoo.com',
  '$2a$04$5C4wKi8gxbyZJNj9sDTKy.cJZ9tYetRtKfwsehpfTAvf7UudbwYD6',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'Tl0FGQKE7bABuO',
  'T7마V다',
  'Tl0FGQKE7bABuO@icloud.com',
  '$2a$04$BVvhKgrPA2rvtBVF.K0K0uqXkuL4gQHjuyDpxjyX2sbvRQkwSaE/O',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T5nw0AYO',
  'TT나Zoz',
  'T5nw0AYO@hanmail.net',
  '$2a$04$70lbefDsPPXKQ1nZrJwv8.qC9.5RTqC6ocmc9RWWzbFtGBvDrBDGG',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'Tu1I1jeNNmiGC9hm2b',
  'T가하바mFfwF',
  'Tu1I1jeNNmiGC9hm2b@naver.com',
  '$2a$04$SD3w8Uis22PNVCKJM9cHde7PbtMv3eFqfrqL4mUQBdBESxvjClJwe',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T8Qki4LwI3WXPe0i',
  'Td타c하T',
  'T8Qki4LwI3WXPe0i@yahoo.com',
  '$2a$04$15xfazfVmLmfrWN1EoVduOPy4m4j8Cqk0R4ZSOONe/OKnxhwsp/ja',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TdJ484K6Pp',
  'T카T5가가jh',
  'TdJ484K6Pp@naver.com',
  '$2a$04$LYcfPprk5hbO7Cn1VnZ5Ke/goziomXHlw4hPb2etmTEStFsVRD8Ci',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TsixxUsSim4izWx9VMG',
  'Ty2Nxl',
  'TsixxUsSim4izWx9VMG@naver.com',
  '$2a$04$/TG37q7nBiKV3WlQwtKCn./FnRx6oqF4O.pvlPX8QvH9TVeUZYXKu',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TL50ZPIjNV6axoH',
  'TPDIh9K',
  'TL50ZPIjNV6axoH@naver.com',
  '$2a$04$71xkZvzpvq19P/8KyfoGlu5vx7EcBlk6wAJZrAMFD2FAjelbk1ypq',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TeHpkh3kOhKtWPS6m',
  'TqLG3b파가',
  'TeHpkh3kOhKtWPS6m@icloud.com',
  '$2a$04$rg28bi2lNBfx7A4kF2MT5OcMgrSCvZeb4FYIhyHn7/Lltymf8FV6i',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T80UrOY7qEjMMVI',
  'Tqvpc',
  'T80UrOY7qEjMMVI@gmail.com',
  '$2a$04$zCM7UtXnuApPtjLaWBa.7eD.Kxi9FnwQDThY5utKaQOjOXCUvfizS',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TDwX4K99t2SSvhjLM4',
  'TnJq0',
  'TDwX4K99t2SSvhjLM4@hanmail.net',
  '$2a$04$FIh5XKlLgm8ezJxVy5WTf.BzxygG0HzM/b.x9SZ8XzwJ352VOZZvK',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'ThKcKQB30UZDBpbvqs',
  'TusMl0fYb',
  'ThKcKQB30UZDBpbvqs@gmail.com',
  '$2a$04$BbF2PqzZ/LOsUAwIU2dEZuzu9duRX1nh810J1DP/WiKoURVIALKSK',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TSo4up1Hzs5ml13h2ma',
  'Tq가fd',
  'TSo4up1Hzs5ml13h2ma@naver.com',
  '$2a$04$QwjVlrICigmRoc5ayG8wruSuj3Dgo6aq4QvklyiPMjjlW.ku5N38y',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T3j5LEjPpyknfMa',
  'TgC자qjWh',
  'T3j5LEjPpyknfMa@naver.com',
  '$2a$04$NqioBrpalGhUHUokitgz6eadFD7EuMTnX8S7kTV0rqYkjPiroB3g.',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TS25SGn',
  'TkDksd',
  'TS25SGn@icloud.com',
  '$2a$04$eUhLQZk/JMe8hNZhThrnd.BgtoikXI2.O.U719qp2/Lcbc2FOp/HS',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'THs0Nv94CwHMa4',
  'T하C1QspYN',
  'THs0Nv94CwHMa4@gmail.com',
  '$2a$04$rQ/lSjOfFjJBkQlCWHtynepOsfQhHAJkry.loyLNqZtAGOYBNaKxm',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TBskNmY63GQaq7n',
  'T2y바aDa마dx',
  'TBskNmY63GQaq7n@gmail.com',
  '$2a$04$Be0nfHmXKYYvZTR06912DOhbtnV/CgUBgJetbIfuAg7LxRCbKTKta',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TP2DUpktk',
  'TKX아s',
  'TP2DUpktk@hanmail.net',
  '$2a$04$CAowUpVEBMXUuG/iS0Y5LeINdsNjfpe.VgbehJ3fLJtKhLZWfLUxu',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T3Bc5Z0ms',
  'TWY나하3FZcx',
  'T3Bc5Z0ms@gmail.com',
  '$2a$04$U.Z4uZehoqI4di3NJzqMUe4BlUhj0CRCyy4Fv4r22hReximoCpgLG',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TQ3AX9SiGJ8sbj8wVJ',
  'TGpj',
  'TQ3AX9SiGJ8sbj8wVJ@yahoo.com',
  '$2a$04$ICB7349smu/a1PddvyOM3.IKOtwjJF63xDt9.ijYQaYADth3kI4SO',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T2uwB4dBfkbc4r3',
  'T6l차44차자카',
  'T2uwB4dBfkbc4r3@yahoo.com',
  '$2a$04$9p2YzjCQQzEsLxcignvePeN6WVQAzeaewOJqyN.WXFL7wRVPHKaja',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TvckL5I1YQod3vVTO2',
  'T7IJ타GC',
  'TvckL5I1YQod3vVTO2@gmail.com',
  '$2a$04$44cnCxSrRqI/Noychou1XuUtxhXnBHVYAIq/JL2p9knnfoFGJwVcm',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TPGHqz3vHIKniv59EYSV',
  'TqXCSBhd',
  'TPGHqz3vHIKniv59EYSV@naver.com',
  '$2a$04$GDmRi3Nwk67t5A.agqlLou3NmWr2WfZiXT4dScVa/XBi3I8DAXo4S',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TcR45y7yOnoi1U5OZp',
  'TTBg',
  'TcR45y7yOnoi1U5OZp@yahoo.com',
  '$2a$04$nZalc0AQwLWofy7ADmDoi.2nKJthASOEI3sDicRekec.IeAygv/jq',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T8iWERHJVg7',
  'T카PR타Q다',
  'T8iWERHJVg7@icloud.com',
  '$2a$04$2cPlR7jeA6EUmgNh5I.CK.V./y2pbf0a82ny2dk1.6.wM/hKBbQem',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T809c0b20fXMWwIAogw',
  'Tpfo아1u사W사',
  'T809c0b20fXMWwIAogw@icloud.com',
  '$2a$04$ucmil9hzLseBuqNzLMv3cOzEZP5TbNGZvei8X7e8U3pgC1XQ/3hs.',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TY0nuRDmeeNcMBG4',
  'TLT라Jl차OI7',
  'TY0nuRDmeeNcMBG4@gmail.com',
  '$2a$04$p4UOBY9ofy8or7ILLNE42e9EqsL4RIyWx6VfPpoWVjcHKAl70Xz3.',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TFGd8SAv07Yrrz5Q',
  'TSFQQ바lR마M',
  'TFGd8SAv07Yrrz5Q@yahoo.com',
  '$2a$04$J4L/T/PaTxtkvVofcJYx2umCvzNAuyMm2HMqS2daD4fTudUTKT5jC',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TlaHpEiCMHK10',
  'TFzVZFDk',
  'TlaHpEiCMHK10@naver.com',
  '$2a$04$xSbxNXGZWsUci.53Qzcem.KbKaY65788NNk.qHsNtbwAPSyQi7WYm',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TXUlg56Ojq',
  'TP하T카WCOXJ',
  'TXUlg56Ojq@hanmail.net',
  '$2a$04$XpOvhIcJzC6H3xqTp2Td3.Y7rUP1pHxT2KQ38490ea85PnNyTQOO2',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TyKCtxN3axOSF9QV2s',
  'TCyN',
  'TyKCtxN3axOSF9QV2s@naver.com',
  '$2a$04$a4L2I939gqoas1tghNto9uz9ZPb.AkZipik0harR5o/vtr99HwxVW',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TjoTTOSP1qw393kOfky',
  'TU3바n',
  'TjoTTOSP1qw393kOfky@icloud.com',
  '$2a$04$b74r0M6Ltb8zYjhMwtxLyuU9lCkPOISAFPdryEx4QTEZQcoyuMmuG',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'ToU56E7',
  'TtoQw자3q아',
  'ToU56E7@gmail.com',
  '$2a$04$71EapPzrtr65QLr2ILXRDO1q8HN7ICN1TgFnR/DDDrFA2LxDxvCtq',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TkXewkaxIyR2jzM',
  'TsAv',
  'TkXewkaxIyR2jzM@hanmail.net',
  '$2a$04$7bKzKwMy6THw7dLNdBwXL.J9k/nE0B62SqsybQFXa5XAv.Kfbuq9q',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TIDp0hpm',
  'Tr차bScYy',
  'TIDp0hpm@naver.com',
  '$2a$04$87fh1uXVwcIK6aatQtY2OusrlfMIDb/16L6kGbo78rKsmm3jd0nw.',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TWTBuNyM34lO6xW',
  'TG81사c',
  'TWTBuNyM34lO6xW@icloud.com',
  '$2a$04$.wYuR9IXEG0Wx3EIVDRj1OtygaWiqJPzU/CE/2kp3w.6kaFdBhiOC',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TyuRHAkxhoJuNt61',
  'TxtyY2G나RP',
  'TyuRHAkxhoJuNt61@hanmail.net',
  '$2a$04$HO7O5pDwhh/kABD52zeY2.CmJWhxa8dCrLV6hbn9aaZkmi3d60aJK',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TTzTYzLezklP9whQ3w',
  'T4p마L',
  'TTzTYzLezklP9whQ3w@yahoo.com',
  '$2a$04$jvGb9s.yGddkZzotSn9S5efuYofaPFDIH0LOUHto9VG93f4ILfpr2',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TBJ06jd',
  'T타UydC카H차',
  'TBJ06jd@icloud.com',
  '$2a$04$kNccp8DMFGKe52Ne1a9zCOZubpQWULm.pJw/wXFSQBFjpJpBL6Ese',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'Tbx8SAmkSx',
  'TXgnoB',
  'Tbx8SAmkSx@gmail.com',
  '$2a$04$X7LUMrvP0WJ62ERmKCThROU0urKlzGdDRvgS97zTh5r0DueIP6x..',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T4heSt0',
  'TC6nM',
  'T4heSt0@gmail.com',
  '$2a$04$2Gf/X24YJPqwT1hCK8bZt.BdAGUA0OzU6pdLIyDvFS3lcA0nJ5G7a',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T2q8z9Fw',
  'TU파Z0K',
  'T2q8z9Fw@naver.com',
  '$2a$04$NLhxaVyOlcF5m/p5kmI42OpVuYjh4mDYCf9F55kfD4z3.8Sg4NgbO',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TbzJEm5',
  'T2바zjl자Z자a',
  'TbzJEm5@icloud.com',
  '$2a$04$Mm7yMHht3yoRfPWY6E5dieITuATrR0ADMYdZnDeiCrBCutMhBYiHa',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'Tchje0XemrP',
  'TlChf',
  'Tchje0XemrP@hanmail.net',
  '$2a$04$3nT.mBCn6uxV06eHGClVNOEYmPQXIfeKv1GPK3auuiFAMXEdvmpFS',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TLDS4bIm1dvEWgQS04t',
  'TH7kWpO',
  'TLDS4bIm1dvEWgQS04t@gmail.com',
  '$2a$04$hr2OXC8wKJfQxAC/5uJ.3u4kYuCUccW89ODYjZdbZBhZCNAZchYX.',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T8FYLB1G9SMWQlgJ',
  'T아나x3iA사hS',
  'T8FYLB1G9SMWQlgJ@hanmail.net',
  '$2a$04$n6WGMHfuZGpS5RSOCWxxj.MakiFat5bk.Y34io5qvn/W7Io0LEwwC',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TZavMUQkP5Mn3Y0TygO',
  'TC0a',
  'TZavMUQkP5Mn3Y0TygO@gmail.com',
  '$2a$04$OI56hLxmScWXbIc8d5Su8.xjMX4MqobqxT96wK0fA35/5w/qX/Tf.',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'Tvy3WCb7qiEbV8Rvs',
  'TipFq바Lqla',
  'Tvy3WCb7qiEbV8Rvs@hanmail.net',
  '$2a$04$XBnp0fv2rNn21IodeQVE8ersAI.AX5/D6Z.y/N9c8LB4nHhHIN1Q.',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TOwGBkp3A6g0Mi',
  'TQctfp',
  'TOwGBkp3A6g0Mi@yahoo.com',
  '$2a$04$M9Kyjl/nqapnsNf7NkgJquIvw3OlHSswnJbbswMl9bU.9pemWODG6',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T4TPflQUlVbF',
  'Tjdcnj',
  'T4TPflQUlVbF@naver.com',
  '$2a$04$Semqa1Ulw4vxB3jvoT9eAuzgsEdzGuD3OyOImX9BELWu5LSxgfR2.',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T3VHQ9hZHBwbbPDBoF',
  'Tn8DW0c',
  'T3VHQ9hZHBwbbPDBoF@gmail.com',
  '$2a$04$nXDdrJEBV.mKQxnROqg0IuSWjM8Xxf1cUAqREW7AlmNdlFcERfYui',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TJu33OhUl0A96IDWDwcn',
  'To차rli9',
  'TJu33OhUl0A96IDWDwcn@gmail.com',
  '$2a$04$u3mdgf2g0XkAq2kozdE5QOrbdYZ/icynuGctt3jP2eqcQPx9FC5BK',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T05JPGw47fO',
  'T6Ro',
  'T05JPGw47fO@gmail.com',
  '$2a$04$/A1J3uSpenRZsUd.QGZtPu6OJp4LhYTglmgNCOQzEhX7fntXKy11G',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TYJjFPiAPkK5NFSsF690',
  'TRHs자PW',
  'TYJjFPiAPkK5NFSsF690@gmail.com',
  '$2a$04$ElvyZ1dT0AbweUzkraInguumD5DONSdWtdEtOeT1ZQKXbHFDkWeXe',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T0MI6BCcTHTCbl0Rq',
  'TbJ7자UXRk',
  'T0MI6BCcTHTCbl0Rq@gmail.com',
  '$2a$04$kQwwWPEKnXFN/77z3hhgs.hEWXcUUq4mI8Z6T0D3eRvTCCOaI6U4.',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TEIOr5i819q0zgs91Nu',
  'TX마HUF',
  'TEIOr5i819q0zgs91Nu@gmail.com',
  '$2a$04$TUbvPILYuESmCceUe/2id..8qBhc6m/0TF6PBUdYvAEoFEJtiQpR.',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T1MKxWEFeOqUDxka',
  'T나hc',
  'T1MKxWEFeOqUDxka@icloud.com',
  '$2a$04$PSNirRfiG15KvWWS/1Rxn.46oY2LONTaDVYp0FQyn7G6HrfBxH6hW',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T0Tv6uAxaiE2OFj',
  'ToTpGIZ',
  'T0Tv6uAxaiE2OFj@naver.com',
  '$2a$04$IE3ERRqL32hscXBUAu8wl.cO/k1bneuPGMdRjcwo.I1zySqU449Vu',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TFqCk3RR4ESc4WZR',
  'T나W나s바파1',
  'TFqCk3RR4ESc4WZR@hanmail.net',
  '$2a$04$8DqlxxS0v5LbjJ4q7zI3jOk5s5IJpPCQdHS1cnbvfId0aljF/3VQC',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T09laiO',
  'TuR차maPk',
  'T09laiO@yahoo.com',
  '$2a$04$tFEaQRIqWXhNvfAytajz.u.QEnJkHJXI4bSkOAslJp.sXzxIARi8C',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TO7uEBM',
  'TjQsth하라u',
  'TO7uEBM@hanmail.net',
  '$2a$04$oTA69EQ.LQEjst2Rvv6kSO5jlOEH9jQzp215vRT.aqOSiHJjKtGY6',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TusHxqIer8Fk0BSrxR',
  'Ta하QZ0ls타자',
  'TusHxqIer8Fk0BSrxR@naver.com',
  '$2a$04$ROazyhWEyBG638OPYKRfye0CEpG5Q8TqmXWLo5BbVtjlIUa.XOYii',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T6oQ19WHv8pbfXp9J',
  'T자yb라xM',
  'T6oQ19WHv8pbfXp9J@icloud.com',
  '$2a$04$v6Oth3eBuumdbMMqxtyC.O3u2EN/WZbfb8e75tbwwX2MAVDI96FMq',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'Tyu0qcCi77EAI3TU',
  'T9사gpX자H',
  'Tyu0qcCi77EAI3TU@naver.com',
  '$2a$04$o1049WprES6PYXGMVczg.OhKHg2vkewp3XK6GMv5rNGOw8fGOC49G',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'Tl3w2ri',
  'T4nAIND',
  'Tl3w2ri@naver.com',
  '$2a$04$Quy07bIQf2prlEMGz7nJJO1J43jyhe3Uo0fcZhLpe6FADvZMmvFM.',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TOeUu7uGe0N3NW9Cp',
  'TES아다N',
  'TOeUu7uGe0N3NW9Cp@hanmail.net',
  '$2a$04$XWnUhQ7wZXiyitHdwb5EE.bmWtmUMgAPOAd8iv4i8rzxSF88hDtnK',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TXeS4Oplh',
  'TIG5카9u0Y',
  'TXeS4Oplh@gmail.com',
  '$2a$04$e2YxSGKhWP7rg7UUcBaKlONuiP9V0qrBPMesASpa1jYZds.AI.1aW',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'Tg3snzJQG',
  'Tv766Gt바',
  'Tg3snzJQG@naver.com',
  '$2a$04$vIxMiQqe0bg0WM9uRKYbaOxbmn0SMUBbaEslkKF6Oq0.3scj6V1Tq',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TUa80KbC4X9g3ovh3UDS',
  'TNoQGly바',
  'TUa80KbC4X9g3ovh3UDS@gmail.com',
  '$2a$04$aF2H0EVJw3.FBFb5UGYkdOSFBAGcEsNFQBn.d5TACAi9UKaV.4Tv6',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TbuMDOBG6mtq5ABT',
  'TOhQI',
  'TbuMDOBG6mtq5ABT@naver.com',
  '$2a$04$aZuwU2j2hz/qvkYl6ZTS2O5HrpKniVOaH605PaNcVXnMTk78YYpHy',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TL3Ko2A8V87UQQvd',
  'TS라2사HCp7S',
  'TL3Ko2A8V87UQQvd@icloud.com',
  '$2a$04$X8A9Z2l03.9c2YhHvTum1eQeENiekIeVY6BRaefq0p/7sm94.3IBm',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'ToyX450NcLZt62Skb',
  'T77나다e',
  'ToyX450NcLZt62Skb@yahoo.com',
  '$2a$04$h/biqPCpXCHJQFkovoBsR.qUz2tvFkC05l8QqUGyeQm/J9VVk8lSm',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T0AOwttNdeKGYu4',
  'TM9나j3가F3M',
  'T0AOwttNdeKGYu4@icloud.com',
  '$2a$04$WOKTvvAMeVK1mKjBtNAJ3ORpbxuZJQB8r2wA3wGhdtZBhVJPFAbdK',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TEjrKd3l4PX',
  'TTw7k자uK카',
  'TEjrKd3l4PX@yahoo.com',
  '$2a$04$nTeg6WP4RI.XEsI.p.aFkuzP68q7z0m/5cubHM59H.5O2Flk4U.wa',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'Tdgiw9o8',
  'TN바마aE',
  'Tdgiw9o8@icloud.com',
  '$2a$04$H4Vu.i.gnNkF0.96TfZUj.smRhJwvqn9Hw1Nlv2dYEpfR90c1N..O',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TDfkyDrmtm58',
  'Thp사B',
  'TDfkyDrmtm58@icloud.com',
  '$2a$04$PZA/.IyZlFC.bGgxw6A6Hu8elOswwAufhutlFhnCSuS4mmTkU0NfK',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TkDakynsSdQpp3PDA',
  'T가8다oniRL',
  'TkDakynsSdQpp3PDA@gmail.com',
  '$2a$04$NS56v.R/MHj9UZpL1gy1telyPwf3RBdHnoM1W/F34c7GzuPuUQiQq',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'T0g5aIkpP',
  'T라YC',
  'T0g5aIkpP@naver.com',
  '$2a$04$h3oKogyvlJ2dQWl./c6L9.1IqEFPCNKndEl.IwCahb74fAlkPrJ4u',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

-- INSERT member
INSERT INTO members (member_id, login_id, nickname, email, password, role, member_status, solved, created_at)
VALUES (
  (SELECT next_val FROM members_seq),
  'TZRoUMt9Bf2mCaBS54e',
  'TOIWVuGo타마',
  'TZRoUMt9Bf2mCaBS54e@gmail.com',
  '$2a$04$CUrd3obgUrfflq.ZkCF9luAVKoTJryQt0GVi3JbM1Oi2.wQgg2q26',
  'GENERAL',
  'ACTIVE',
  0,
  now()
);

UPDATE members_seq SET next_val = next_val + 1;

COMMIT;



commit;
set
autocommit = 1;