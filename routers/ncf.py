from fastapi import APIRouter

ncf = APIRouter(prefix='/ncf')

@ncf.get('/',tags=['ncf'])
async def start_ncf():
    return {'msg' : 'Here is NCF'}

@ncf.get('/model', tags = ['ncf'])
async def ncf_model():
    return {'msg' : 'Here is NCF model'} 

print("원격저장소에서 직접 수정을 두 번째로 했어요! 이건 pull을 안했을 때 어떤 충돌과 오류가 나는지 확인하기 위함입니다-!!!")
