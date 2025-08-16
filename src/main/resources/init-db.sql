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

commit;
set
autocommit = 1;