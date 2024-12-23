from fastapi import APIRouter

nfm = APIRouter(prefix = '/nfm')

@nfm.get('/', tags = ['nfm'])
async def start_nfm():
    return {'msg':'Here is NFM'}

@nfm.get('/model',tags = ['nfm'])
async def nfm_model():
    return {'msg':'Here is NFM model'}

print("원격저장소에서 직접 수정을 했어요 test4!!!")
