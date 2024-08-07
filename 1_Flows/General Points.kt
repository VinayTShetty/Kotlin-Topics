Hot and Cold

Hot:- Data will be produced , even if there is no consumenr.
       Data lost will not be recovered.
       Hot Streams needs to be closed manually.

Cold:- Data will be produced based on consumer demand.
        Data can be recocovered.
        If there is no consumer,then the Cold stream will be closed automatically.



Producer and Consumer Problem.

Producer :- 
              Producer is producing data very fast.

Consumer :- 
              Consumer not able to process all the data , as its very slow
              This is called bottleneck.
              We can solve this probelem using buffering.
              In this case, producer is producing more data.Store the data in the buffer and process later.
              We should maintain the capacity of buffer.i.e how much capacity buffer can hold the data.


              Alternative/Vice-versa also can happen.
              Producer is slow and consumer is fast.

              In this these cases we will block the Thread.
              In kotlin we use coroutines,so that Thread won t be blocked.It will be suspended.


              
Flows :- 
        Flows are Cold,If there is no consumenr, Producer won t generate data.
        Flow will be cancelled if there are no producer.



        In Case of Hot Streams data will be produced , even there is not consumer.
        In case of Hot Stremas once data is lost , it cannot be received.