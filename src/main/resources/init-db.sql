set
autocommit = 0;

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
        now());

UPDATE problems_seq SET next_val = 1004;

INSERT INTO languages (language_id, name, extension, created_at) VALUES (1001, "Java", "java", now());
INSERT INTO versions (version_id, language_id, version_name, created_at) VALUES (1001, 1001, "Java 8", now());
INSERT INTO versions (version_id, language_id, version_name, created_at) VALUES (1002, 1001, "Java 11", now());
INSERT INTO versions (version_id, language_id, version_name, created_at) VALUES (1003, 1001, "Java 15", now());
INSERT INTO versions (version_id, language_id, version_name, created_at) VALUES (1004, 1001, "Java 17", now());

UPDATE languages_seq SET next_val = 1002;
UPDATE versions_seq SET next_val = 1005;

INSERT INTO testcases (testcase_id, problem_id, testcase_input_name, testcase_input_url, testcase_output_name, testcase_output_url, is_public, created_at)
    VALUES (1, 1000, "input01", "", "output01", "", false, now()),
         (2, 1000, "input02", "", "output02", "", false, now()),
         (3, 1000, "input03", "", "output03", "", false, now()),
         (4, 1000, "input04", "", "output04", "", false, now()),
         (5, 1000, "input05", "", "output05", "", true, now());
INSERT INTO testcases (testcase_id, problem_id, testcase_input_name, testcase_input_url, testcase_output_name, testcase_output_url, is_public, created_at)
VALUES (6, 1001, "input01", "", "output01", "", false, now()),
       (7, 1001, "input02", "", "output02", "", false, now()),
       (8, 1001, "input03", "", "output03", "", false, now()),
       (9, 1001, "input04", "", "output04", "", false, now()),
       (10, 1001, "input05", "", "output05", "", false, now()),
       (11, 1001, "input06", "", "output06", "", false, now()),
       (12, 1001, "input07", "", "output07", "", false, now()),
       (13, 1001, "input08", "", "output08", "", false, now()),
       (14, 1001, "input09", "", "output09", "", false, now()),
       (15, 1001, "input10", "", "output10", "", false, now()),
       (16, 1001, "input11", "", "output11", "", false, now()),
       (17, 1001, "input12", "", "output12", "", true, now()),
       (18, 1001, "input13", "", "output13", "", true, now()),
       (19, 1001, "input14", "", "output14", "", false, now()),
       (20, 1001, "input15", "", "output15", "", false, now());
INSERT INTO testcases (testcase_id, problem_id, testcase_input_name, testcase_input_url, testcase_output_name, testcase_output_url, is_public, created_at)
VALUES (21, 1002, "input01", "", "output01", "", false, now()),
       (22, 1002, "input02", "", "output02", "", false, now()),
       (23, 1002, "input03", "", "output03", "", false, now()),
       (24, 1002, "input04", "", "output04", "", false, now()),
       (25, 1002, "input05", "", "output05", "", false, now()),
       (26, 1002, "input06", "", "output06", "", false, now()),
       (27, 1002, "input07", "", "output07", "", true, now()),
       (28, 1002, "input08", "", "output08", "", false, now()),
       (29, 1002, "input09", "", "output09", "", true, now()),
       (30, 1002, "input10", "", "output10", "", false, now()),
       (31, 1002, "input11", "", "output11", "", false, now());
INSERT INTO testcases (testcase_id, problem_id, testcase_input_name, testcase_input_url, testcase_output_name, testcase_output_url, is_public, created_at)
VALUES (32, 1003, "input01", "", "output01", "", false, now()),
       (33, 1003, "input02", "", "output02", "", false, now()),
       (34, 1003, "input03", "", "output03", "", false, now()),
       (35, 1003, "input04", "", "output04", "", false, now()),
       (36, 1003, "input05", "", "output05", "", false, now()),
       (37, 1003, "input06", "", "output06", "", false, now()),
       (38, 1003, "input07", "", "output07", "", true, now()),
       (39, 1003, "input08", "", "output08", "", false, now()),
       (40, 1003, "input09", "", "output09", "", false, now()),
       (41, 1003, "input10", "", "output10", "", false, now()),
       (42, 1003, "input11", "", "output11", "", false, now()),
       (43, 1003, "input12", "", "output12", "", false, now()),
       (44, 1003, "input13", "", "output13", "", true, now()),
       (45, 1003, "input14", "", "output14", "", true, now()),
       (46, 1003, "input15", "", "output15", "", false, now()),
       (47, 1003, "input16", "", "output16", "", false, now()),
       (48, 1003, "input17", "", "output17", "", false, now()),
       (49, 1003, "input18", "", "output18", "", false, now()),
       (50, 1003, "input19", "", "output19", "", false, now());

UPDATE testcases
SET
    testcase_input_url = CONCAT('https://algoduck-testcase-bucket-ec2-810270402.s3.ap-northeast-2.amazonaws.com/prob_', LPAD(problem_id, 5, '0'), '/', testcase_input_name),
    testcase_output_url = CONCAT('https://algoduck-testcase-bucket-ec2-810270402.s3.ap-northeast-2.amazonaws.com/prob_', LPAD(problem_id, 5, '0'), '/', testcase_output_name);

UPDATE testcases_seq SET next_val = 51;

commit;
set
autocommit = 1;