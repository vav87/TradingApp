insert into signals (id, signal_id, methods, description) values (1, 1, 'setUp;setAlgoParam:1:60;performCalc;submitToMarket', 'signal 1');
insert into signals (id, signal_id, methods, description) values (2, 2, 'reverse;setAlgoParam:1:80;submitToMarket', 'signal 2');
insert into signals (id, signal_id, methods, description) values (3, 3, 'setAlgoParam:1:90;setAlgoParam:2:15;performCalc;submitToMarket', 'signal 3');
insert into signals (id, signal_id, methods, description) values (4, 4, 'setParam:1:90;performCalc;submitToMarket', 'signal 4 (wrong method)');
insert into signals (id, signal_id, methods, description) values (5, 5, 'setAlgoParam:qwerty:asdfg;performCalc;submitToMarket', 'signal 5 (wrong param)');
